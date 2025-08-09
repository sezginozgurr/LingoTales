package com.app.lingotales.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lingotales.core.datastore.PreferencesKeys
import com.app.lingotales.core.datastore.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    fun onGetStartedClick() {
        viewModelScope.launch {
            preferencesManager.saveBoolean(PreferencesKeys.IS_FIRST_RUN, false)
        }
    }
} 