package com.kalu.growit.feature.onboarding

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import com.kalu.growit.compose_ui.R
import com.kalu.growit.compose_ui.components.CustomTextField
import com.kalu.growit.compose_ui.components.GrowItSolidRoundedButton
import com.kalu.growit.compose_ui.theme.PrimaryColor
import com.kalu.growit.core.util.LottieAnim
import com.kalu.growit.feature.onboarding.navigator.OnboardingNavigator
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber


@Destination
@Composable
fun AccountLookUp(
    navigator: OnboardingNavigator, viewModel: OnboardingViewModel = hiltViewModel()
) {


    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleAwareAuthFlow = remember(viewModel.accountLookResponse, lifecycleOwner) {
        viewModel.accountLookResponse.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    @SuppressLint("StateFlowValueCalledInComposition") val accLookUpState by lifecycleAwareAuthFlow.collectAsState(
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

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .verticalScroll(
                rememberScrollState()
            ), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = com.kalu.growit.compose_ui.R.string.acc_look_up),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                text = stringResource(id = com.kalu.growit.compose_ui.R.string.acc_look_up_desc),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                LottieAnim(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(300.dp),
                    resId = R.raw.lottie_look_up
                )
            }
        }

        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 8.dp),
                text = stringResource(id = R.string.full_names),
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
                placeholderText = stringResource(id = R.string.ex_evans_name)

            ) {
                run {
                    fullName = it
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 8.dp),
                text = stringResource(id = R.string.id_no),
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
                placeholderText = stringResource(id = R.string.ex_id_no)
            ) {
                run {
                    idNo = it
                }
            }



            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    modifier = Modifier.absoluteOffset((-12).dp, 0.dp),
                    checked = checked,
                    onCheckedChange = { checked_ ->
                        checked = checked_
                    },
                    colors = CheckboxDefaults.colors(PrimaryColor)
                )
                val annotatedString = buildAnnotatedString {
                    append("I agree to the ")
                    pushStringAnnotation(tag = "policy", annotation = "https://google.com/policy")
                    withStyle(style = SpanStyle(color = PrimaryColor)) {
                        append("Terms & Conditions")
                    }
                    pop()

                }

                ClickableText(text = annotatedString,
                    style = MaterialTheme.typography.bodySmall,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(
                            tag = "policy", start = offset, end = offset
                        ).firstOrNull()?.let {}

                        annotatedString.getStringAnnotations(
                            tag = "terms", start = offset, end = offset
                        ).firstOrNull()?.let {
                            Log.d("terms URL", it.item)
                        }
                    })


            }


            Spacer(modifier = Modifier.height(24.dp))
            GrowItSolidRoundedButton(buttonText = "Fetch") {
                navigator.openDeviceVerification("254746882415")
            }
        }

    }
}

