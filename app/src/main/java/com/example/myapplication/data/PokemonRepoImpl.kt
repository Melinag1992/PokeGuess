package com.example.myapplication.data

import com.example.myapplication.data.model.Pokemon
import com.example.myapplication.data.retrofit.PokemonApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PokemonRepoImpl(private  val api : PokemonApi): PokemonRepo {
    override suspend fun getPokemons(): Flow<Results<List<Pokemon>>> {
        return  flow {

            val pokemonFromRepo = try {
                api.getPokemon()

            } catch ( e:IOException){
                e.printStackTrace()
                emit(Results.Error(msg= "Error loading Data"))
                return@flow

            }catch ( e: Exception){
                e.printStackTrace()
                emit(Results.Error(msg = "Error loading Data"))
                return@flow
            }
            emit(Results.Success(pokemonFromRepo.results))
        }
    }
}