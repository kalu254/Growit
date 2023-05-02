package com.kalu.growit.feature.onboarding

import android.app.Activity
import android.content.*
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.kalu.growit.compose_ui.R
import com.kalu.growit.compose_ui.theme.PrimaryColor
import com.kalu.growit.core.util.LottieAnim
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber


@Destination
@Composable
fun DeviceVerificationScreen(phoneNumber: String) {

    var shouldResendOtp by remember {
        mutableStateOf(false)
    }


    SmsRetrieverUserConsentBroadcast(shouldResendOtp = shouldResendOtp) { _, code ->
        Timber.e(code)
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.device_verification),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                text = stringResource(id = R.string.device_verification_desc, phoneNumber),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
,                horizontalArrangement = Arrangement.Center
            ) {
                LottieAnim(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(300.dp), resId = R.raw.lottie_device_verification
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Column() {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondaryContainer,),
                    shape = RoundedCornerShape(6.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (i in 0..5) {
                            Card(
                                modifier = Modifier.size(50.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White,),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 10.dp
                                )

                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = "1",
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                }
                            }

                        }
                    }

                }
                Spacer(
                    modifier = Modifier
                        .height(24.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Timer()
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            val annotatedString = buildAnnotatedString {
                append("Didn’t get code?")
                pushStringAnnotation(tag = "policy", annotation = "")
                withStyle(style = SpanStyle(color = PrimaryColor)) {
                    append("Resend")
                }
                pop()

            }
            ClickableText(
                text = annotatedString,
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

    }
}

@Composable
fun SmsRetrieverUserConsentBroadcast(
    shouldResendOtp: Boolean,
    smsCodeLength: Int = 6,
    onSmsReceived: (message: String, code: String) -> Unit,
) {
    val context = LocalContext.current

    var shouldRegisterReceiver by remember { mutableStateOf(false) }


    LaunchedEffect(shouldResendOtp) {
        SmsRetriever.getClient(context)
            .startSmsUserConsent(null)
            .addOnSuccessListener {
                shouldRegisterReceiver = true
            }
    }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it?.resultCode == Activity.RESULT_OK && it.data != null) {
                val message: String? = it.data!!.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                message?.let {
                    val verificationCode = getVerificationCodeFromSms(message, smsCodeLength)

                    onSmsReceived(message, verificationCode)
                }
                shouldRegisterReceiver = false
            } else {
            }
        }

    if (shouldRegisterReceiver) {
        SystemBroadcastReceiver(
            systemAction = SmsRetriever.SMS_RETRIEVED_ACTION,
            broadCastPermission = SmsRetriever.SEND_PERMISSION,
        ) { intent ->
            if (intent != null && SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                val extras = intent.extras

                val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
                when (smsRetrieverStatus.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        // Get consent intent
                        val consentIntent =
                            extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                        try {
                            // Start activity to show consent dialog to user, activity must be started in
                            // 5 minutes, otherwise you'll receive another TIMEOUT intent
                            launcher.launch(consentIntent)
                        } catch (e: ActivityNotFoundException) {
                            Timber.e("Activity Not found for SMS consent API")
                        }
                    }

                    CommonStatusCodes.TIMEOUT -> {
                    }
                }
            }
        }
    }
}

@Composable
fun SystemBroadcastReceiver(
    systemAction: String,
    broadCastPermission: String,
    onSystemEvent: (intent: Intent?) -> Unit
) {
    // Grab the current context in this part of the UI tree
    val context = LocalContext.current

    // Safely use the latest onSystemEvent lambda passed to the function
    val currentOnSystemEvent by rememberUpdatedState(onSystemEvent)

    // If either context or systemAction changes, unregister and register again
    DisposableEffect(context, systemAction) {
        val intentFilter = IntentFilter(systemAction)
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                onSystemEvent(intent)
            }
        }

        context.registerReceiver(broadcast, intentFilter)

        // When the effect leaves the Composition, remove the callback
        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }

    DisposableEffect(context, broadCastPermission) {
        val intentFilter = IntentFilter(broadCastPermission)
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                onSystemEvent(intent)
            }
        }

        context.registerReceiver(broadcast, intentFilter)

        // When the effect leaves the Composition, remove the callback
        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}

internal fun getVerificationCodeFromSms(sms: String, smsCodeLength: Int): String =
    sms.filter { it.isDigit() }
        .substring(0 until smsCodeLength)

@Composable
fun Timer() {
    val millisInFuture: Long = 10 * 3000
    val timeData = remember {
        mutableStateOf(millisInFuture)
    }

    val countDownTimer =
        object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeData.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
            }
        }

    DisposableEffect(key1 = "key") {
        countDownTimer.start()
        onDispose {
            countDownTimer.cancel()
        }
    }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        text = "This session will expire in ${timeData.value} seconds",
        style = MaterialTheme.typography.labelMedium,
        textAlign = TextAlign.Center
    )
}



