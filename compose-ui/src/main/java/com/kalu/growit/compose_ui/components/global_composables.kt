package com.kalu.growit.compose_ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kalu.growit.compose_ui.theme.PrimaryColor
import com.kalu.growit.compose_ui.theme.PrimaryTextColor

@Composable
fun GrowItOutlinedRoundedButton(buttonText: String, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(percent = 50),
        border = BorderStroke(width = 1.dp, color = PrimaryColor),
    )
    {
        Text(
            buttonText,
            color = PrimaryColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Composable
fun GrowItSolidRoundedButton(buttonText: String, onClick: () -> Unit) {
    Button(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(
            MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(50)
        ), onClick = { onClick.invoke() }) {
        Text(
            buttonText,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelAndPlaceHolderTextField(label: String, placeHolder: String, onText: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onText(text.text)
        },
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolder) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceHolderTextField(
    keyboardType: KeyboardType,
    placeHolder: String,
    onText: (String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onValueChange = {
            text = it
            onText(text.text)
        },
        placeholder = { Text(text = placeHolder) },
        textStyle = TextStyle(fontSize = 10.sp)
    )
}


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    keyboardType: KeyboardType = KeyboardType.Text,
    fontSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize,
    onTextAvailable: (String) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }

    BasicTextField(modifier = modifier
        .background(
            MaterialTheme.colorScheme.surface,
            MaterialTheme.shapes.small,
        )
        .border(
            width = 1.dp,
            color = PrimaryColor,
            shape = RoundedCornerShape(size = 4.dp)
        )
        .fillMaxWidth(),

        value = text,
        onValueChange = {
            text = it
            onTextAvailable.invoke(text)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(
                    Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}
