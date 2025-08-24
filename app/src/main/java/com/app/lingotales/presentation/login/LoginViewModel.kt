package com.app.lingotales.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lingotales.core.datastore.PreferencesKeys
import com.app.lingotales.core.datastore.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun login(userName: String) {
        viewModelScope.launch {
            try {
                preferencesManager.saveString(PreferencesKeys.USER_NAME, userName)
            } catch (e: Exception) {
                Timber.e(e, "Kullanıcı adı kaydedilirken hata: ${e.message}")
            }
        }
    }
}
