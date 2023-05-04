package com.kalu.growit.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kalu.growit.compose_ui.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SetSecurityQuestionsScreen() {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Text(
            text = stringResource(id = R.string.set_security_question),
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = stringResource(id = R.string.please_select_questions_to_answer),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}