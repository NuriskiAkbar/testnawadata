package com.example.pokedex.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.ZoneOffset

interface ApiEndpoint {

    @GET(" ")
    fun getAllListPokemon(): Call<ResponseListApiPokemon>

    @GET(" ")
    fun getAllMoreListPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<ResponseListApiPokemon>

}