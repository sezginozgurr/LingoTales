package com.app.lingotales.util.extension

import android.os.Build
import androidx.annotation.RequiresExtension
import com.app.lingotales.core.network.ApiException
import com.app.lingotales.core.network.RestResponse
import com.app.lingotales.core.network.RestResult
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> safeApiCallWithRestResponse(call: suspend () -> RestResponse<T>): RestResult<T> {
    return try {
        val response = call()
        timber.log.Timber.d("API Response: succeeded=${response.succeeded}, error=${response.error}, data=${response.data}")
        
        if (response.succeeded && response.data != null) {
            timber.log.Timber.d("API Success - returning data")
            RestResult.Success(response.data)
        } else {
            timber.log.Timber.e("API Error - succeeded=${response.succeeded}, error=${response.error}")
            RestResult.Failure(
                ApiException(
                    response.error ?: "Unknown error",
                )
            )
        }

    } catch (e: HttpException) {
        timber.log.Timber.e(e, "HttpException: ${e.message}")
        RestResult.Failure(throwable = extractErrorResponse(e) ?: e)
    } catch (e: Exception) {
        timber.log.Timber.e(e, "Exception: ${e.message}")
        RestResult.Failure(throwable = e)
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
suspend fun <T> safeApiCallWithResponse(call: suspend () -> Response<T>): RestResult<T> {
    return try {
        val response = call()


        if (response.isSuccessful && response.body() != null) {
            RestResult.Success(response.body()!!)
        } else {
            RestResult.Failure(
                ApiException(
                    response.message().orEmpty(),
                    status = response.code()
                )
            )
        }

    } catch (e: HttpException) {
        RestResult.Failure(throwable = e)

    } catch (ex: Exception) {
        RestResult.Failure(throwable = ex)
    }
}

private fun extractErrorResponse(httpException: HttpException): ApiException? {
    return try {
        val errorBody = httpException.response()?.errorBody()?.string()
        if (errorBody != null) {
            Gson().fromJson(errorBody, ApiException::class.java)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}
