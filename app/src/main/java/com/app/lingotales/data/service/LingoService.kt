package com.app.lingotales.data.service

import com.app.lingotales.core.network.RestResponse
import com.app.lingotales.data.model.MobileCategoriesResponse
import retrofit2.http.GET

interface LingoService {
    
    @GET("mobile/categories")
    suspend fun getMobileCategories(): RestResponse<MobileCategoriesResponse>
}