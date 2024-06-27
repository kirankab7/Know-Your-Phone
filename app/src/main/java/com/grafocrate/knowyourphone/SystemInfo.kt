package com.grafocrate.knowyourphone


data class SystemInfo(
    val androidVersion: String,
    val apiLevel: String,
    val securityPatchLevel: String,
    val bootloader: String,
    val buildId: String,
    val javaVM: String,
    val openGLES: String,
    val kernelArchitecture: String,
    val kernelVersion: String,
    val rootAccess: String,
    val googlePlayServices: String,
    val systemUptime: String
)
