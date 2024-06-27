package com.grafocrate.knowyourphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BatteryViewModel(private val repository: BatteryRepository) : ViewModel() {

    private val _batteryInfo = MutableLiveData<Result<BatteryInfo>>()
    val batteryInfo: LiveData<Result<BatteryInfo>> get() = _batteryInfo

    init {
        fetchBatteryInfo()
    }

    private fun fetchBatteryInfo() {
        viewModelScope.launch {
            val info = repository.getBatteryInfo()
            _batteryInfo.postValue(info)
        }
    }
}
