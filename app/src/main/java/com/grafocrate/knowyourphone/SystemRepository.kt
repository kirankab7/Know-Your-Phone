package com.grafocrate.knowyourphone

import android.content.Context
import android.os.Build
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.ConnectionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class SystemRepository(private val context: Context) {

    suspend fun getSystemInfo(): SystemInfo {
        return withContext(Dispatchers.IO) {
            val androidVersion = Build.VERSION.RELEASE
            val apiLevel = Build.VERSION.SDK_INT.toString()
            val securityPatchLevel = getSecurityPatchLevel()
            val bootloader = Build.BOOTLOADER
            val buildId = Build.ID
            val javaVM = System.getProperty("java.vm.version") ?: "Unknown"
            val openGLES = getOpenGLESVersion()
            val kernelArchitecture = System.getProperty("os.arch") ?: "Unknown"
            val kernelVersion = getKernelVersion()
            val rootAccess = if (isDeviceRooted()) "Yes" else "No"
            val googlePlayServices = getGooglePlayServicesStatus()
            val systemUptime = getSystemUptime()

            SystemInfo(
                androidVersion = androidVersion,
                apiLevel = apiLevel,
                securityPatchLevel = securityPatchLevel,
                bootloader = bootloader,
                buildId = buildId,
                javaVM = javaVM,
                openGLES = openGLES,
                kernelArchitecture = kernelArchitecture,
                kernelVersion = kernelVersion,
                rootAccess = rootAccess,
                googlePlayServices = googlePlayServices,
                systemUptime = systemUptime
            )
        }
    }

    private fun getSecurityPatchLevel(): String {
        return try {
            val fields = Build.VERSION::class.java.getFields()
            fields.find { it.name == "SECURITY_PATCH" }?.get(null) as? String ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    private fun getOpenGLESVersion(): String {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
        val configurationInfo = activityManager.deviceConfigurationInfo
        return configurationInfo.glEsVersion
    }

    private fun getKernelVersion(): String {
        try {
            val process = Runtime.getRuntime().exec("uname -r")
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            return reader.readLine()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "Unknown"
    }

    private fun isDeviceRooted(): Boolean {
        val paths = arrayOf(
            "/sbin/su", "/system/bin/su", "/system/xbin/su",
            "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
            "/system/bin/failsafe/su", "/data/local/su"
        )
        return paths.any { File(it).exists() }
    }

    private fun getGooglePlayServicesStatus(): String {
        val gmsAvailability = GoogleApiAvailability.getInstance()
        val resultCode = gmsAvailability.isGooglePlayServicesAvailable(context)
        return if (resultCode == ConnectionResult.SUCCESS) "Available" else "Unavailable"
    }

    private fun getSystemUptime(): String {
        val uptime = System.currentTimeMillis() - android.os.SystemClock.elapsedRealtime()
        return formatDuration(uptime)
    }

    private fun formatDuration(duration: Long): String {
        val seconds = (duration / 1000) % 60
        val minutes = (duration / (1000 * 60)) % 60
        val hours = (duration / (1000 * 60 * 60)) % 24
        val days = (duration / (1000 * 60 * 60 * 24))
        return String.format("%d days, %02d:%02d:%02d", days, hours, minutes, seconds)
    }
}
