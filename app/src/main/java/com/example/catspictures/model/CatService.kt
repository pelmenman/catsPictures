package com.example.catspictures.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CatService {
    @GET("images/search")
    fun getImage(): Call<CatImage>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1/"
        fun create(): CatService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(CatService::class.java)
        }
    }
}