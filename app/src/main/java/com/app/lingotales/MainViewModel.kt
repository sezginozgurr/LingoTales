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

            savedPhoneNumber != null -> {
                _initialDestination.value = Destination.Login
            }

            else -> {
                _initialDestination.value = Destination.Login
            }
        }
    }

    fun updateDestination() {
        autManager.clearAll()
        _initialDestination.value = Destination.Login
    }

    private fun checkFirstRun() { //todo
        viewModelScope.launch {
            val savedPhoneNumber = autManager.getPhoneNumber()

            val isFirstRun = preferencesManager
                .getBoolean(PreferencesKeys.IS_FIRST_RUN)
                .first() ?: true

            dismissSplash(savedPhoneNumber, isFirstRun)
        }
    }
}