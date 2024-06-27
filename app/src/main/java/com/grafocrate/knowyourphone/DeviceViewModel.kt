package com.grafocrate.knowyourphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DeviceViewModel(private val repository: DeviceRepository) : ViewModel() {

    private val _deviceInfo = MutableLiveData<DeviceInfo>()
    val deviceInfo: LiveData<DeviceInfo> get() = _deviceInfo

    init {
        fetchDeviceInfo()
    }

    private fun fetchDeviceInfo() {
        viewModelScope.launch {
            val info = repository.getDeviceInfo()
            _deviceInfo.postValue(info)
        }
    }
}
