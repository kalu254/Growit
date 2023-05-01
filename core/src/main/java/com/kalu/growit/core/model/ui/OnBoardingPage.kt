package com.kalu.growit.core.model.ui

import com.kalu.growit.core.R

sealed class OnBoardingPage(
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = com.kalu.growit.compose_ui.R.raw.lottie_page_one,
        title = "Farmer Registration",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Second : OnBoardingPage(
        image = com.kalu.growit.compose_ui.R.raw.lottie_page_two,
        title = "Agrovet Registration",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Third : OnBoardingPage(
        image = com.kalu.growit.compose_ui.R.raw.lottie_page_three,
        title = "Farm Mapping",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Fourth : OnBoardingPage(
        image = com.kalu.growit.compose_ui.R.raw.lottie_page_four,
        title = "Farm Inputs distribution",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}