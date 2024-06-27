package com.grafocrate.knowyourphone

data class BatteryInfo(
    val health: String,
    val level: Int,
    val powerSource: String,
    val status: String,
    val technology: String,
    val temperature: Float,
    val voltage: Int
)
