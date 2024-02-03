package com.uncp.myproyectosesion5.data.respository

import com.uncp.myproyectosesion5.data.models.ResponseModel
import com.uncp.myproyectosesion5.data.network.DataService

class RepositoryService {
    private val api = DataService()

    suspend fun getAllData(): ResponseModel? {
        return api.getData()
    }
}