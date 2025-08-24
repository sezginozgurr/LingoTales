package com.app.lingotales

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lingotales.core.datastore.PreferencesKeys
import com.app.lingotales.core.datastore.PreferencesManager
import com.app.lingotales.core.manager.AuthManager
import com.app.lingotales.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(
    private val autManager: AuthManager,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _initialDestination = MutableStateFlow<Destination>(Destination.Empty)
    val initialDestination: StateFlow<Destination> = _initialDestination

    init {
        checkFirstRun()
    }

    private fun dismissSplash(savedPhoneNumber: String?, isFirstRun: Boolean) {
        when {
            isFirstRun -> {
                _initialDestination.value = Destination.Onboarding
            }
            else -> {
                checkUserLoginStatus()
            }
        }
    }

    private fun checkUserLoginStatus() {
        viewModelScope.launch {
            val userName = preferencesManager.getString(PreferencesKeys.USER_NAME).first()
            
            if (!userName.isNullOrEmpty()) {
                _initialDestination.value = Destination.Choose
            } else {
                _initialDestination.value = Destination.Login
            }
        }
    }

    fun updateDestination() {
        autManager.clearAll()
        _initialDestination.value = Destination.Login
    }

    fun logout() {
        viewModelScope.launch {
            try {
                preferencesManager.removeKey(PreferencesKeys.USER_NAME)
                _initialDestination.value = Destination.Login
            } catch (e: Exception) {
                Timber.e(e, "Logout sırasında hata: ${e.message}")
            }
        }
    }

    private fun checkFirstRun() {
        viewModelScope.launch {
            val savedPhoneNumber = autManager.getPhoneNumber()

            val isFirstRun = preferencesManager
                .getBoolean(PreferencesKeys.IS_FIRST_RUN)
                .first() ?: true

            dismissSplash(savedPhoneNumber, isFirstRun)
        }
    }
}