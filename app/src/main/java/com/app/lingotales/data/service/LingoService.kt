package com.app.lingotales.data.service

import com.app.lingotales.core.network.RestResponse
import com.app.lingotales.data.model.MobileCategoriesResponse
import com.app.lingotales.data.model.CategoryBooksResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LingoService {
    
    @GET("mobile/categories")
    suspend fun getMobileCategories(): RestResponse<MobileCategoriesResponse>
    
    @GET("mobile/categories/{categoryId}/books")
    suspend fun getCategoryBooks(@Path("categoryId") categoryId: Int): RestResponse<CategoryBooksResponse>
}