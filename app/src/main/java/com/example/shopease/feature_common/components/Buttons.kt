package com.example.shopease.feature_common.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.HorizontalDistribute
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopease.feature_common.utils.ShopAppConstants


/* App Buttons

   This page contains app reusable buttons
 */


@Composable
fun AppMenuBtn(
    onClick : () -> Unit
){
    
    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(ShopAppConstants.AppPrimaryColor)
        ),
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "App menu btn",
            tint = Color(ShopAppConstants.AppIconColor),
            modifier = Modifier.size(23.dp)
        )
    }
}


@Composable
fun BackIconButton(
    color: Color = Color(ShopAppConstants.AppPrimaryColor),
    onClick : () -> Unit
){

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIos,
            contentDescription = "App menu btn",
            tint = color,
            modifier = Modifier.size(23.dp)
        )
    }
}


@Composable
fun FilterBtn(
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isPressed) Color.LightGray else Color.White // Change color on press

        ),
        modifier = Modifier
            .animateContentSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        // Await the press and then invoke the onClick action
                        tryAwaitRelease() // This will wait until the user releases the press
                        isPressed = false
                    },
                    onTap = {
                        onClick.invoke()
                    }
                )
            }
            .width(60.dp)
            .height(60.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(0.3.dp, color = Color(ShopAppConstants.CardBorderColor))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.HorizontalDistribute,
                contentDescription = "App menu btn",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}



@Composable
fun MoreIconButton(
    color: Color = Color(ShopAppConstants.AppPrimaryColor),
    onClick : () -> Unit
){

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "App menu btn",
            tint = color,
            modifier = Modifier.size(23.dp)
        )
    }
}



@Composable
fun MinusButton(
    onClick : () -> Unit
){

    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(ShopAppConstants.AppPrimaryColor)
        ),
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Text(
            text = "-",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
fun PlusButton(
    onClick : () -> Unit
){

    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(ShopAppConstants.AppPrimaryColor)
        ),
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Text(
            text = "+",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}



@Composable
fun TxtButton(
    text:String,
    `font-size`: TextUnit,
    backgroundColor: Color = Color(ShopAppConstants.AppPrimaryColor),
    textColor : Color = Color.White,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    enabled :Boolean = true,
    contentPadding : PaddingValues = PaddingValues(5.dp),
    icon: Boolean = false,
    iconFound: ImageVector = Icons.Default.KeyboardArrowDown,
    onClick : () -> Unit
){


    val image = if(icon) iconFound else null
    TextButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = shape,
        enabled = enabled,
        contentPadding = contentPadding,
        onClick = {
            onClick.invoke()
        }
    ) {
        Text(
            text = text,
            fontSize = `font-size`,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace,
        )
        if(icon) {
            if (image != null) {
                Icon(imageVector = image, contentDescription = "", modifier = Modifier.padding(horizontal = 5.dp), tint = Color.Black)
            }
        }
    }
}


@Composable
fun CartIconBtn(
    onClick : () -> Unit
){

    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(ShopAppConstants.AppPrimaryColor)
        ),
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "", tint = Color(ShopAppConstants.AppIconColor))
    }
}







@Composable
fun PrimaryActionBtn(
    text: String,
    textColor: Color = Color.White,
    enabled: Boolean = true,
    fontSize: TextUnit = 13.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
    fontFamily: FontFamily = FontFamily.SansSerif,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color(ShopAppConstants.AppPrimaryColor),
        contentColor = Color.White,
    ),
    shape: RoundedCornerShape = RoundedCornerShape(14.dp),
    contentPadding: PaddingValues = PaddingValues(7.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    modifier : Modifier = Modifier,
    onClick: () -> Unit,
){

    TextButton(
        colors  = buttonColors,
        enabled = enabled,
        shape = shape,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        modifier = modifier,
        onClick = {
        onClick.invoke()
    }) {
        Text(
            textAlign = TextAlign.Center,
            text = text,
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily,

        )
    }
}



@Composable
fun iconBtn(
    icon:ImageVector,
    iconColor:Color = Color.Black,
    enabled: Boolean = true,
    colors: IconButtonColors ,
    modifier: Modifier = Modifier,
    iconModifier : Modifier = Modifier,
    onClick: () -> Unit,
){

    IconButton(
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        onClick = {
            onClick.invoke()
        }
    )
    {
      Icon(imageVector = icon, contentDescription = "", tint = iconColor , modifier = iconModifier)
    }

}
