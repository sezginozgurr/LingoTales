package com.app.lingotales.core.network

data class ApiException(
    override val message: String?,
    val status: Int?,
) : Throwable()