package com.grafocrate.knowyourphone

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SensorsRepository(private val context: Context) {

    suspend fun getSensorsInfo(): List<SensorInfo> {
        return withContext(Dispatchers.IO) {
            val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
            sensors.map {
                SensorInfo(
                    name = it.name,
                    type = it.type,
                    vendor = it.vendor,
                    version = it.version,
                    maxRange = it.maximumRange,
                    resolution = it.resolution,
                    power = it.power,
                    minDelay = it.minDelay
                )
            }
        }
    }
}
