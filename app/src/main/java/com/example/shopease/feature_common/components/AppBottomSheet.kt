package com.example.shopease.feature_common.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    showContent : MutableState<Boolean>,
    onDismissRequest : () -> Unit,
    containerColor : Color = Color.White,
    contentColor :Color = Color.Black,
    content: @Composable () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if(showContent.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            containerColor = containerColor,
            contentColor = contentColor ,
            onDismissRequest = {
               onDismissRequest.invoke()
            }
        ) {
            content.invoke()
        }
    }
}