package com.grafocrate.knowyourphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ThermalViewModel(private val repository: ThermalRepository) : ViewModel() {

    private val _thermalInfo = MutableLiveData<ThermalInfo>()
    val thermalInfo: LiveData<ThermalInfo> get() = _thermalInfo

    init {
        fetchThermalInfo()
    }

    private fun fetchThermalInfo() {
        viewModelScope.launch {
            val info = repository.getThermalInfo()
            _thermalInfo.postValue(info)
        }
    }
}
