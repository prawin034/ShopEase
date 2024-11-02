package com.example.shopease.feature_common.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shopease.feature_common.utils.ShopAppConstants


@Composable
fun SearchBarCmn(
    value :String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onValueChange : (String) -> Unit
)
{



    OutlinedTextField(
        shape = RoundedCornerShape(28.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black
        ),
        modifier = modifier

            .padding(0.dp),

        value = value,
        onValueChange = {
        onValueChange.invoke(it)
    },

        trailingIcon = {

        },
        label = {
            Row {
                Text(
                    text = "Search",
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(bottom = 0.dp), // Align label vertically
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

        }
    )




}