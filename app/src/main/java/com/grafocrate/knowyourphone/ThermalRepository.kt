package com.grafocrate.knowyourphone

import android.content.Context
import android.os.BatteryManager
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ThermalRepository(private val context: Context) {

    suspend fun getThermalInfo(): ThermalInfo {
        return withContext(Dispatchers.IO) {
            val batteryTemperature = getBatteryTemperature()
            val cpuTemperature = getCpuTemperature()
            // Implement other temperature fetching as needed

            ThermalInfo(
                batteryTemperature = batteryTemperature.toString(),
                cpuTemperature = cpuTemperature,
                gpuTemperature = 0.0f, // Placeholder for GPU temperature
                skinTemperature = 0.0f, // Placeholder for skin temperature
                ambientTemperature = 0.0f // Placeholder for ambient temperature
            )
        }
    }

    private fun getBatteryTemperature(): String {
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return batteryManager.getIntProperty(BatteryManager.EXTRA_TEMPERATURE)
    }

    private fun getCpuTemperature(): Float {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val process = Runtime.getRuntime().exec("cat /sys/class/thermal/thermal_zone0/temp")
            process.waitFor()
            val reader = process.inputStream.bufferedReader()
            val temperature = reader.readLine()?.toFloatOrNull() ?: 0.0f
            reader.close()
            return temperature / 1000.0f // Convert to Celsius
        } else {
            // Fallback for older devices
            return 0.0f // Handle older devices as needed
        }
    }
}

private fun BatteryManager.getIntProperty(extraTemperature: String): String {
    return ((BatteryManager.EXTRA_TEMPERATURE)).toString()
}
