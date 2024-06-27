package com.grafocrate.knowyourphone

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BatteryRepository(private val context: Context) {

    suspend fun getBatteryInfo(): Result<BatteryInfo> {
        return withContext(Dispatchers.IO) {
            try {
                val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
                val batteryStatus = context.registerReceiver(null, intentFilter)
                batteryStatus?.let {
                    val health = getHealth(it.getIntExtra(BatteryManager.EXTRA_HEALTH, -1))
                    val level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    val powerSource = getPowerSource(it.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1))
                    val status = getStatus(it.getIntExtra(BatteryManager.EXTRA_STATUS, -1))
                    val technology = it.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY) ?: "Unknown"
                    val temperature = it.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10f
                    val voltage = it.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)

                    val batteryInfo = BatteryInfo(
                        health = health,
                        level = level,
                        powerSource = powerSource,
                        status = status,
                        technology = technology,
                        temperature = temperature,
                        voltage = voltage
                    )
                    Result.success(batteryInfo)
                } ?: Result.failure(Exception("Battery information not available"))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun getHealth(health: Int): String {
        return when (health) {
            BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
            BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over Voltage"
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified Failure"
            BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
            else -> "Unknown"
        }
    }

    private fun getPowerSource(plugged: Int): String {
        return when (plugged) {
            BatteryManager.BATTERY_PLUGGED_AC -> "AC"
            BatteryManager.BATTERY_PLUGGED_USB -> "USB"
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Wireless"
            else -> "Battery"
        }
    }

    private fun getStatus(status: Int): String {
        return when (status) {
            BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
            BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
            BatteryManager.BATTERY_STATUS_FULL -> "Full"
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not Charging"
            else -> "Unknown"
        }
    }
}
