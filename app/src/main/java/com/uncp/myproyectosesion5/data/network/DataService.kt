package com.uncp.myproyectosesion5.data.network

import com.uncp.myproyectosesion5.core.RetrofitHelper
import com.uncp.myproyectosesion5.data.models.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DataService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getData(): ResponseModel? {
        return withContext(Dispatchers.IO) {
            val response: Response<ResponseModel> = retrofit.create(ApiClient::class.java).getDataFromService()
            response.body()
        }
    }
}