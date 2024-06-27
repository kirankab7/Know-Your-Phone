package com.grafocrate.knowyourphone

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ThermalViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThermalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThermalViewModel(ThermalRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
