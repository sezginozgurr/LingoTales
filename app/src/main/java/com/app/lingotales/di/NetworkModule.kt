package com.app.lingotales.di

import android.content.Context
import com.app.lingotales.data.service.LingoService
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val TIMEOUT = 60L
    private const val BASE_URL = "https://storybook.volerycompany.site/api/"

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context
    ): ChuckerInterceptor = ChuckerInterceptor(context)

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Timber.d(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        chucker: ChuckerInterceptor,
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(chucker)
        addInterceptor(logging)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideMobileApiService(
        retrofit: Retrofit
    ): LingoService = retrofit.create(LingoService::class.java)
}
