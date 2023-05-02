package com.kalu.growit.navigation

import com.kalu.growit.feature.onboarding.destinations.AccountLookUpDestination
import com.kalu.growit.feature.onboarding.destinations.DeviceVerificationScreenDestination
import com.kalu.growit.feature.onboarding.destinations.SplashScreenDestination
import com.kalu.growit.feature.onboarding.destinations.WalkThroughScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object NavGraphs {

    val onboarding = object : NavGraphSpec {
        override val route = "onboarding"

        override val startRoute = SplashScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            SplashScreenDestination,
            WalkThroughScreenDestination,
            AccountLookUpDestination,
            DeviceVerificationScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
    }


    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = onboarding
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(
           onboarding
        )
    }
}