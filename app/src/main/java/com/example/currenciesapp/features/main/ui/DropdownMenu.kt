package com.example.currenciesapp.features.main.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.remote.model.Currency
import com.example.uikit.R
import com.example.uikit.utills.clickable
import com.example.uikit.style.*
import com.example.uikit.utills.Drawables

@Composable
fun DropdownMenu(
    selected: Currency,
    currencies: List<Currency>,
    onChangeCurrency: (Currency) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier) {
        Row(
            Modifier
                .width(290.dp)
                .border(
                    width = 1.dp,
                    color = Colors.secondaryMain,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .clickable {
                    expanded = !expanded
                }
                .background(
                    color = Colors.defaultBg, RoundedCornerShape(8.dp)
                )
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f), text = selected.name,
                style = TextStyle.default,
                color = Colors.textDefault
            )
            Icon(
                painter = painterResource(id = Drawables.ic_arrow_down),
                contentDescription = "Arrow icon",
                tint = Colors.primaryMain
            )

        }

        DropdownMenu(modifier = Modifier
            .width(290.dp)
            .border(
                width = 1.dp,
                color = Colors.secondaryMain,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .background(
                color = Colors.defaultBg,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .padding(horizontal = 0.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {

            currencies.forEach {
                DropdownMenuItem(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Colors.defaultBg),
                    text = {
                        Text(
                            text = it.name,
                            style = TextStyle.default,
                            color = Colors.textDefault
                        )
                    },
                    onClick = {
                        onChangeCurrency(it)
                        expanded = false
                    })

            }
        }
    }

}
