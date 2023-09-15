package com.example.catspictures.model

import retrofit2.Call
import retrofit2.http.GET

interface CatService {
    @GET("images/search")
    suspend fun getImage(): List<CatImageItem>
}