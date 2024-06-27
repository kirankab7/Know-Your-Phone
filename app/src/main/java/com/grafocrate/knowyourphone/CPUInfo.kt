package com.grafocrate.knowyourphone

data class CPUInfo(
    val model: String,
    val cores: Int,
    val architecture: String,
    val clockSpeed: String,
    val numberOfCpus: Int,
    val gpu: String
)
