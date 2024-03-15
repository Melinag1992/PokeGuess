package com.example.myapplication.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroFitInstance {

    // interceptor for our network calls to view what is happening while calls are being made
    private  val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // building our client
    private  val client :OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    // if we wanted to use a different api we would just create an additional instance

    val api : PokemonApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(PokemonApi.BASE_URL)
        .client(client)
        .build()
        .create(PokemonApi::class.java)


}