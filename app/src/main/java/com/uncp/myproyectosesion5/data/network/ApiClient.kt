package com.uncp.myproyectosesion5.data.network

import com.uncp.myproyectosesion5.data.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("top-headlines?country=us&category=business&apiKey=54154168496246459b1c8bda93823981")
    suspend fun getDataFromService(): Response<ResponseModel>
}