package com.kalu.growit.navigation

import androidx.navigation.NavController
import com.kalu.growit.feature.onboarding.WalkThroughNavigator
import com.kalu.growit.feature.onboarding.destinations.WalkThroughScreenDestination
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class CoreFeatureNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : WalkThroughNavigator {

    override fun moveNext() {
        navController.navigate(WalkThroughScreenDestination within navGraph )
    }

    override fun lookUp() {
        TODO("Not yet implemented")
    }

}