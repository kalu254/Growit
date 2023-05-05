package com.kalu.growit.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kalu.growit.compose_ui.R
import com.kalu.growit.compose_ui.components.CustomTextField
import com.kalu.growit.compose_ui.components.GrowItSolidRoundedButton
import com.kalu.growit.compose_ui.components.GrowitAutoCompleteTextView
import com.kalu.growit.feature.onboarding.navigator.OnboardingNavigator
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SetSecurityQuestionsScreen(navigator: OnboardingNavigator) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxSize()
            .background(Color.Red)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Text(
            text = stringResource(id = R.string.set_security_question),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(id = R.string.please_select_questions_to_answer),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onTertiary
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .fillMaxHeight(),
        ) {
            /**
             * quiz one
             */
            Text(
                text = stringResource(id = R.string.select_quiz_one, "Q1"),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 14.sp
            )
            GrowitAutoCompleteTextView(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(4.dp)
                    .height(48.dp),
                fontSize = 10.sp,
                options = listOf("One", "Two", "Three", "Four")
            ) {
                run {}
            }
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(
                leadingIcon = null,
                trailingIcon = null,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(4.dp)
                    .height(48.dp),
                fontSize = 10.sp,
                placeholderText = stringResource(id = R.string.question_goes_here)
            ) {
            }

            Spacer(modifier = Modifier.height(24.dp))

            /**
             * quiz two
             */
            Text(
                text = stringResource(id = R.string.select_quiz_one, "Q2"),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 14.sp

            )
            GrowitAutoCompleteTextView(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(4.dp)
                    .height(48.dp),
                fontSize = 10.sp,
                options = listOf("One", "Two", "Three", "Four")
            ) {
                run {}
            }
            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                leadingIcon = null,
                trailingIcon = null,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(4.dp)
                    .height(48.dp),
                fontSize = 10.sp,
                placeholderText = stringResource(id = R.string.question_goes_here)
            ) {
            }
            Spacer(modifier = Modifier.height(24.dp))

            /**
             * quiz two
             */

            Text(
                text = stringResource(id = R.string.select_quiz_one, "Q3"),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 14.sp

            )
            GrowitAutoCompleteTextView(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(4.dp)
                    .height(48.dp),
                fontSize = 10.sp,
                options = listOf("One", "Two", "Three", "Four")
            ) {
                run {}
            }
            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                leadingIcon = null,
                trailingIcon = null,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(percent = 50)
                    )
                    .padding(4.dp)
                    .height(48.dp),
                fontSize = 10.sp,
                placeholderText = stringResource(id = R.string.question_goes_here)
            ) {
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            GrowItSolidRoundedButton(buttonText = stringResource(id = R.string.submit)) {
            }
        }

    }
}