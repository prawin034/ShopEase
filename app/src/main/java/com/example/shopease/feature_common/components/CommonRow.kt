package com.example.shopease.feature_common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CommonRow(
    modifier: Modifier = Modifier.fillMaxWidth(),
    enableDefaultPadding : Boolean = false,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable () -> Unit
){
    val paddingValues = if(enableDefaultPadding)  PaddingValues(5.dp) else paddingValues

   Row(
       modifier = modifier
           .padding(paddingValues),
       horizontalArrangement = horizontalArrangement,
       verticalAlignment = verticalAlignment,

   ) {
       content()
   }
}



@Composable
fun `Col-2-row`(
    row1Space : Float = 0.5f,
    row2Space : Float = 0.5f,
    row1 : @Composable () -> Unit,
    row2 : @Composable () -> Unit
){
    Row(
       horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 4.dp)
            .padding(5.dp)
            .fillMaxWidth(1f)
    ) {

        Column(modifier = Modifier.fillMaxWidth(row1Space).padding(bottom = 7.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
            row1()
        }


        Column(modifier = Modifier.fillMaxWidth(row2Space).padding(bottom = 7.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
            row2()
        }



    }

}