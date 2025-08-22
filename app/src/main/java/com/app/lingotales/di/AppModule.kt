package com.app.lingotales.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.app.lingotales.core.manager.AuthManager
import com.app.lingotales.core.manager.AuthManagerImpl
import com.app.lingotales.core.manager.SecureSharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("app_preferences")
        }

    @Provides
    @Singleton
    fun provideSecureSharedPrefs(
        @ApplicationContext context: Context
    ): SecureSharedPrefs = SecureSharedPrefs(context)

    @Provides
    @Singleton
    fun provideAuthManager(
        securePrefs: SecureSharedPrefs
    ): AuthManager = AuthManagerImpl(securePrefs)
}
