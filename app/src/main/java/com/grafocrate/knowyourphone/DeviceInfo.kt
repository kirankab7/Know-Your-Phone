package com.grafocrate.knowyourphone

data class DeviceInfo(
    val model: String,
    val manufacturer: String,
    val brand: String,
    val board: String,
    val hardware: String,
    val screenSize: String,
    val screenResolution: String,
    val screenDensity: String,
    val totalRam: String,
    val availableRam: String,
    val internalStorage: String,
    val availableInternalStorage: String,
    val externalStorage: String?,
    val availableExternalStorage: String?
)
