package com.grafocrate.knowyourphone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CPUViewModel(private val repository: CPURepository) : ViewModel() {

    private val _cpuInfo = MutableLiveData<CPUInfo>()
    val cpuInfo: LiveData<CPUInfo> get() = _cpuInfo

    init {
        fetchCPUInfo()
    }

    private fun fetchCPUInfo() {
        viewModelScope.launch {
            val info = repository.getCPUInfo()
            _cpuInfo.postValue(info)
        }
    }
}
