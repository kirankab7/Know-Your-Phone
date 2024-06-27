package com.grafocrate.knowyourphone

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.DisplayMetrics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class DeviceRepository(private val context: Context) {

    suspend fun getDeviceInfo(): DeviceInfo {
        return withContext(Dispatchers.IO) {
            val model = Build.MODEL
            val manufacturer = Build.MANUFACTURER
            val brand = Build.BRAND
            val board = Build.BOARD
            val hardware = Build.HARDWARE

            val displayMetrics = context.resources.displayMetrics
            val screenSize = "${displayMetrics.widthPixels / displayMetrics.density} x ${displayMetrics.heightPixels / displayMetrics.density}"
            val screenResolution = "${displayMetrics.widthPixels} x ${displayMetrics.heightPixels}"
            val screenDensity = "${displayMetrics.densityDpi} dpi"

            val memoryInfo = ActivityManager.MemoryInfo()
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityManager.getMemoryInfo(memoryInfo)
            val totalRam = formatSize(memoryInfo.totalMem)
            val availableRam = formatSize(memoryInfo.availMem)

            val internalStorage = getStorageSize(Environment.getDataDirectory())
            val availableInternalStorage = getAvailableStorageSize(Environment.getDataDirectory())
            val externalStorage = getStorageSize(context.getExternalFilesDir(null))
            val availableExternalStorage = getAvailableStorageSize(context.getExternalFilesDir(null))

            DeviceInfo(
                model = model,
                manufacturer = manufacturer,
                brand = brand,
                board = board,
                hardware = hardware,
                screenSize = screenSize,
                screenResolution = screenResolution,
                screenDensity = screenDensity,
                totalRam = totalRam,
                availableRam = availableRam,
                internalStorage = internalStorage,
                availableInternalStorage = availableInternalStorage,
                externalStorage = externalStorage,
                availableExternalStorage = availableExternalStorage
            )
        }
    }

    private fun getStorageSize(path: File?): String {
        return path?.let {
            val stat = android.os.StatFs(it.path)
            val blockSize = stat.blockSizeLong
            val totalBlocks = stat.blockCountLong
            formatSize(totalBlocks * blockSize)
        } ?: "Unavailable"
    }

    private fun getAvailableStorageSize(path: File?): String {
        return path?.let {
            val stat = android.os.StatFs(it.path)
            val blockSize = stat.blockSizeLong
            val availableBlocks = stat.availableBlocksLong
            formatSize(availableBlocks * blockSize)
        } ?: "Unavailable"
    }

    private fun formatSize(size: Long): String {
        var sizeInKB = size / 1024
        if (sizeInKB < 1024) return "$sizeInKB KB"
        var sizeInMB = sizeInKB / 1024
        if (sizeInMB < 1024) return "$sizeInMB MB"
        val sizeInGB = sizeInMB / 1024
        return "$sizeInGB GB"
    }
}
