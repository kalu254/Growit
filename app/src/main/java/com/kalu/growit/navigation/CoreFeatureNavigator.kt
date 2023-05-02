package com.kalu.growit.navigation

import androidx.navigation.NavController
import com.kalu.growit.feature.onboarding.destinations.AccountLookUpDestination
import com.kalu.growit.feature.onboarding.destinations.DeviceVerificationScreenDestination
import com.kalu.growit.feature.onboarding.destinations.WalkThroughScreenDestination
import com.kalu.growit.feature.onboarding.navigator.OnboardingNavigator
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class CoreFeatureNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : OnboardingNavigator {

    override fun moveNext() {
        navController.navigate(WalkThroughScreenDestination within navGraph )
    }

    override fun getStarted() {
        navController.navigate(AccountLookUpDestination within navGraph)
    }

    override fun openDeviceVerification(phoneNumber: String) {
        navController.navigate(DeviceVerificationScreenDestination(phoneNumber) within navGraph)
    }

}