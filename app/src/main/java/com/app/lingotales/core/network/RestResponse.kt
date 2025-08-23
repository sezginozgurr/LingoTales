package com.app.lingotales.core.network

data class RestResponse<T>(
    val succeeded: Boolean,
    val error: String? = null,
    val data: T
)
