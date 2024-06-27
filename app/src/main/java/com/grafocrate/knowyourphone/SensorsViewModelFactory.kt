package com.grafocrate.knowyourphone

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SensorsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SensorsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SensorsViewModel(SensorsRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
