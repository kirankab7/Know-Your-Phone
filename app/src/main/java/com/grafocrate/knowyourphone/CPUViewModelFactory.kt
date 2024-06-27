package com.grafocrate.knowyourphone


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CPUViewModelFactory(private val repository: CPURepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CPUViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CPUViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
