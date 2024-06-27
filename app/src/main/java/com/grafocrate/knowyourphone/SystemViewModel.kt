package com.grafocrate.knowyourphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SystemViewModel(private val repository: SystemRepository) : ViewModel() {

    private val _systemInfo = MutableLiveData<SystemInfo>()
    val systemInfo: LiveData<SystemInfo> get() = _systemInfo

    init {
        fetchSystemInfo()
    }

    private fun fetchSystemInfo() {
        viewModelScope.launch {
            val info = repository.getSystemInfo()
            _systemInfo.postValue(info)
        }
    }
}
