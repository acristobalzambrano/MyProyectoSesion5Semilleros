package com.uncp.myproyectosesion5.domain

import com.uncp.myproyectosesion5.data.models.ResponseModel
import com.uncp.myproyectosesion5.data.respository.RepositoryService

class GetDataUseCase {
    private val repository = RepositoryService()

    suspend operator fun invoke(): ResponseModel? {
        return repository.getAllData()
    }
}