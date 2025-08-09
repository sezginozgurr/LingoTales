package com.app.lingotales.util.extension

fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true