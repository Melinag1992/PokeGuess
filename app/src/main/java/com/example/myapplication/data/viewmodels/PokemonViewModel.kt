package com.example.myapplication.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.PokemonRepo
import com.example.myapplication.data.Results
import com.example.myapplication.data.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonViewModel( repo: PokemonRepo) : ViewModel() {

    private  val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    var pokemons  = _pokemons.asStateFlow()

    var isError = false ;

    init {
        viewModelScope.launch {
            repo.getPokemons().collectLatest {result ->
                when(result){
                    is Results.Error ->{
                        isError = true
                    }
                    is Results.Success ->{
                        result.data?.let {  pokemonList ->
                            _pokemons.update { pokemonList }
                        }
                    }

                }
            }
        }
    }
}