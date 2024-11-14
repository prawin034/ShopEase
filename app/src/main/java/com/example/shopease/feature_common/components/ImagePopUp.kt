package com.example.shopease.feature_common.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties


@Composable
fun ImagePopUp(
    state : MutableState<Boolean> ,
    onDismiss : () -> Unit,
    content: @Composable () -> Unit,

) {

    if(state.value) {
        Popup(

            alignment = Alignment.Center,
            onDismissRequest = {
                onDismiss.invoke()
            },
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            content()

        }
    }


}