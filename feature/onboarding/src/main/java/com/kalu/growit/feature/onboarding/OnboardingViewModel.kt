package com.kalu.growit.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {

    private var _accountLookResponse = MutableStateFlow(false)
    val accountLookResponse: StateFlow<Boolean> = _accountLookResponse

    fun fetchAccount() {
        viewModelScope.launch {
            delay(1000)
            _accountLookResponse.emit(true)
        }
    }

}