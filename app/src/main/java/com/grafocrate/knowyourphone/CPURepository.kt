package com.grafocrate.knowyourphone

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.os.Build
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class CPURepository {

    suspend fun getCPUInfo(): CPUInfo {
        return withContext(Dispatchers.IO) {
            val model = getCPUModel()
            val cores = Runtime.getRuntime().availableProcessors()
            val architecture = System.getProperty("os.arch")
            val clockSpeed = getCPUClockSpeed()
            val numberOfCpus = cores // Simplified
            val gpu = getGPUInfo()

            CPUInfo(
                model = model,
                cores = cores,
                architecture = architecture ?: "Unknown",
                clockSpeed = clockSpeed,
                numberOfCpus = numberOfCpus,
                gpu = gpu
            )
        }
    }

    private fun getCPUModel(): String {
        var model = "Unknown"
        try {
            val reader = BufferedReader(FileReader("/proc/cpuinfo"))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                if (line!!.contains("Hardware")) {
                    model = line!!.split(":")[1].trim()
                    break
                }
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return model
    }

    private fun getCPUClockSpeed(): String {
        return try {
            val reader = BufferedReader(FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"))
            val clockSpeed = reader.readLine()
            reader.close()
            "${clockSpeed.toInt() / 1000} MHz"
        } catch (e: IOException) {
            "Unknown"
        }
    }

    private fun getGPUInfo(): String {
        // This is a placeholder. In real implementation, fetching GPU info is complex
        // and might require accessing OpenGL APIs or specific vendor details.
        return "Unknown"
    }
}
