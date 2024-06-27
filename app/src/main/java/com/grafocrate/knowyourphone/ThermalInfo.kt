package com.grafocrate.knowyourphone

data class ThermalInfo(
    val batteryTemperature: String,
    val cpuTemperature: Float,
    val gpuTemperature: Float,
    val skinTemperature: Float,
    val ambientTemperature: Float
)
