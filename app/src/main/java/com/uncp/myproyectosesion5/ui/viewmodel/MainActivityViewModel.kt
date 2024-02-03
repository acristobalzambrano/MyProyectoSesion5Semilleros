package com.uncp.myproyectosesion5.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uncp.myproyectosesion5.data.models.ResponseModel
import com.uncp.myproyectosesion5.domain.GetDataUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val _dataResponse = MutableLiveData<ResponseModel?>()
    val dataResponse: LiveData<ResponseModel?> = _dataResponse

    var getDataUseCase = GetDataUseCase()

    fun getListDataApi() {
        viewModelScope.launch {
            val result: ResponseModel? = getDataUseCase()
            if (result != null) {
                _dataResponse.postValue(result)
            }
        }
    }
}