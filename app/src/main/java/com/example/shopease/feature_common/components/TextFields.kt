package com.example.shopease.feature_common.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun CmnTxtField(
    value: String,
    enabled :Boolean = true,
    keyboardOptions: KeyboardOptions =KeyboardOptions(keyboardType = KeyboardType.Text),
    label : @Composable () -> Unit,
    leadingIcon : @Composable () -> Unit,
    trailingIcon : @Composable () -> Unit,
    placeHolder : @Composable () -> Unit,
    maxLines :Int = 1,
    singleLine :Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(11.dp),
    colors: TextFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color.White,
        focusedContainerColor = Color.White,
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        unfocusedPlaceholderColor = Color.Black,
        focusedPlaceholderColor = Color.Black,
        unfocusedLabelColor = Color.Black

        ),
    visualTransformation: VisualTransformation,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
)
{

    OutlinedTextField(
        value = value,
        enabled = enabled,
        colors = colors,
        label = {
            label.invoke()
        },
        placeholder = {
            placeHolder.invoke()
        },
        trailingIcon = {
            trailingIcon.invoke()
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines ,
        singleLine = singleLine,
        shape = shape,
        textStyle = textStyle,
        modifier = modifier,
        onValueChange = {
            onValueChange.invoke(it)
        },
    )



}