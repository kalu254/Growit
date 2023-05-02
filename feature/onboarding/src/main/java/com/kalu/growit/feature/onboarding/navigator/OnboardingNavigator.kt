package com.kalu.growit.feature.onboarding.navigator

interface OnboardingNavigator{
    fun moveNext()
    fun getStarted()
    fun openDeviceVerification(phoneNumber: String)
}