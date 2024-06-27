package com.grafocrate.knowyourphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SensorsViewModel(private val repository: SensorsRepository) : ViewModel() {

    private val _sensorsInfo = MutableLiveData<List<SensorInfo>>()
    val sensorsInfo: LiveData<List<SensorInfo>> get() = _sensorsInfo

    init {
        fetchSensorsInfo()
    }

    private fun fetchSensorsInfo() {
        viewModelScope.launch {
            val info = repository.getSensorsInfo()
            _sensorsInfo.postValue(info)
        }
    }
}
