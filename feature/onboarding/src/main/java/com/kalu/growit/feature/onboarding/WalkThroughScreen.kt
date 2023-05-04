@file:OptIn(ExperimentalAnimationApi::class)

package com.kalu.growit.feature.onboarding

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.kalu.growit.compose_ui.R.*
import com.kalu.growit.compose_ui.components.GrowItOutlinedRoundedButton
import com.kalu.growit.compose_ui.components.GrowItSolidRoundedButton
import com.kalu.growit.compose_ui.theme.PrimaryTextColor
import com.kalu.growit.core.model.ui.OnBoardingPage
import com.kalu.growit.core.util.LottieAnim
import com.kalu.growit.feature.onboarding.navigator.OnboardingNavigator
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



@Destination
@Composable
fun SplashScreen(
    navigator: OnboardingNavigator
) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.2f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(100)
        navigator.moveNext()
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = drawable.banque),
            contentDescription = "Logo"
        )
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
@Destination
fun WalkThroughScreen(navigator: OnboardingNavigator) {

    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        val pages = listOf(
            OnBoardingPage.First,
            OnBoardingPage.Second,
            OnBoardingPage.Third,
            OnBoardingPage.Fourth
        )
        val pagerState = rememberPagerState()

        Column(
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxHeight(0.6f),
                count = 4,
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { position ->
                PagerScreen(onBoardingPage = pages[position])

            }

            HorizontalPagerIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(30.dp),
                pagerState = pagerState
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                WalkthroughButtons(
                    scope = scope,
                    state = pagerState,
                    onClickGetStarted = {
                        navigator.getStarted()
                    }
                )
            }

        }

    }
}


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WalkthroughButtons(
    scope: CoroutineScope,
    state: PagerState,
    onClickGetStarted: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GrowItSolidRoundedButton(stringResource(id = string.get_started)) {
            onClickGetStarted.invoke()
        }
        Spacer(modifier = Modifier.height(16.dp))
        GrowItOutlinedRoundedButton(stringResource(id = string.continue_str)) {
            scope.launch {
                state.scrollToPage(state.currentPage + 1)
            }
        }

    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        LottieAnim(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            resId = onBoardingPage.image
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            text = onBoardingPage.title,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = onBoardingPage.description,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = PrimaryTextColor
        )
    }
}



