package com.example.uikit.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uikit.R
import com.example.uikit.style.*
import com.example.uikit.utills.Drawables
import com.example.uikit.utills.clickable

@Preview(showBackground = true)
@Composable
fun StyledKit.IconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int = Drawables.ic_filter,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier
            .background(
                color = Colors.defaultBg, RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Colors.secondaryMain,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .padding(vertical = 4.dp)
            .then(modifier),
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.defaultBg
        ),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "Button Icon",
            tint = Colors.primaryMain
        )
    }
}

@Composable
fun StyledKit.Button(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = Colors.primaryMain,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(8.dp)
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = TextStyle.button)
    }
}
