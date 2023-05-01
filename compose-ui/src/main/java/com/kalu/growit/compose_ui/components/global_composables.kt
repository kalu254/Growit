package com.kalu.growit.compose_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kalu.growit.compose_ui.theme.PrimaryColor

@Composable
fun GrowItOutlinedRoundedButton(onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth().height(50.dp),
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(percent = 50),
        border = BorderStroke(width = 1.dp, color = PrimaryColor),
    )
    {
        Text(
            "Continue",
            color = PrimaryColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Composable
fun GrowItSolidRoundedButton(onClick: () -> Unit) {
    Button(modifier = Modifier
        .fillMaxWidth().height(50.dp)
        .background(
            MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(50)
        ), onClick = { onClick.invoke() }) {
        Text(
            "Get Started",
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }

}