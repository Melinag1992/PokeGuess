package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.data.PokemonRepoImpl
import com.example.myapplication.data.model.Pokemon
import com.example.myapplication.data.retrofit.RetroFitInstance
import com.example.myapplication.data.viewmodels.PokemonViewModel
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityMainBinding


    val pokeViewModel by viewModels<PokemonViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PokemonViewModel(PokemonRepoImpl(RetroFitInstance.api))
                            as T
                }
            }
        })
    val versionModel by viewModels<PokemonViewModel>(
        factoryProducer = {
            object  : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PokemonViewModel(PokemonRepoImpl(RetroFitInstance.api))
                            as T
                }
            }
        }
    )

    val viewModel by viewModels<PokemonViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PokemonViewModel(PokemonRepoImpl(RetroFitInstance.api))
                    as T
                }
            }
        }
    )

    lateinit var  pokemonList : List<Pokemon>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        pokemonList = viewModel.pokemons.value

        println("$pokemonList")


    }


    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, fragment)
                .addToBackStack(fragment.javaClass.name) // gives us name of fragment
        }
    }
}