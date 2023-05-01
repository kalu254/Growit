package com.kalu.growit

import androidx.lifecycle.ViewModel
import com.kalu.growit.core.domain.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userDataRepository: UserDataRepository
) : ViewModel() {
    val theme = userDataRepository.themeStream
}