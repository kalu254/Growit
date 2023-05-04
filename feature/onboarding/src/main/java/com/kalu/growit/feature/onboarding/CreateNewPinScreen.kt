package com.kalu.growit.feature.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import com.kalu.growit.compose_ui.R
import com.kalu.growit.compose_ui.components.CustomTextField
import com.kalu.growit.compose_ui.components.GrowItSolidRoundedButton
import com.kalu.growit.core.util.LottieAnim
import com.kalu.growit.feature.onboarding.navigator.OnboardingNavigator
import com.ramcosta.composedestinations.annotation.Destination
import java.util.Locale

@Destination
@Composable
fun CreateNewScreen(
    navigator: OnboardingNavigator,
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleAwareAuthFlow = remember(viewModel.accountLookResponse, lifecycleOwner) {
        viewModel.accountLookResponse.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    val accLookUpState by lifecycleAwareAuthFlow.collectAsState(
        viewModel.accountLookResponse.value
    )


    var fullName by remember {
        mutableStateOf("")
    }

    var idNo by remember {
        mutableStateOf("")
    }

    var checked by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .imePadding()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.change_default_pin),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = stringResource(id = R.string.default_pin_desc),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    LottieAnim(
                        resId = R.raw.lottie_shield
                    )
                }
            }
            Column() {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    text = stringResource(id = R.string.new_pin),
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp
                )

                CustomTextField(
                    trailingIcon = null,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surface,
                        )
                        .height(45.dp),
                    fontSize = 10.sp,
                    placeholderText = stringResource(id = R.string.ex_1234)

                ) {
                    run {
                        fullName = it
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    text = stringResource(id = R.string.confirm_new_pin),
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp
                )
                CustomTextField(
                    trailingIcon = null,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surface,
                        )
                        .height(45.dp),
                    fontSize = 10.sp,
                    keyboardType = KeyboardType.Number,
                    placeholderText = stringResource(id = R.string.ex_1234)
                ) {
                    run {
                        idNo = it
                    }
                }

            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(50.dp)
                .fillMaxWidth()
                .background(Color.Red)
        )

    }
}