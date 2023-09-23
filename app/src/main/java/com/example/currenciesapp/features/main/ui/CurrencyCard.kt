package com.example.currenciesapp.features.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.currenciesapp.features.main.model.RateUI
import com.example.uikit.R
import com.example.uikit.style.Colors
import com.example.uikit.style.TextStyle
import com.example.uikit.utills.*

@Composable
fun CurrencyCard(
    rate: RateUI,
    onFavoriteClicked: (RateUI) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(color = Colors.cardBg, shape = RoundedCornerShape(size = 12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        rate.fromCurrency?.let {
            Text(
                text = it.name + "/",
                style = TextStyle.default,
                color = Colors.textDefault
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = rate.toCurrency.name,
            style = TextStyle.default,
            color = Colors.textDefault
        )

        Text(
            text = rate.value.toString(),
            style = TextStyle.defaultBold,
            color = Colors.textDefault
        )

        SpacerWidth(width = 16.dp)

        if (rate.isFavorite) {
            Icon(
                modifier = Modifier.clickable { onFavoriteClicked(rate) },
                painter = painterResource(id = Drawables.ic_favorites),
                contentDescription = "Favorites on Icon",
                tint = Colors.yellowMain
            )
        } else {
            Icon(
                modifier = Modifier.clickable { onFavoriteClicked(rate) },
                painter = painterResource(id = Drawables.ic_favorites_off),
                contentDescription = "Favorites off Icon",
                tint = Colors.secondaryMain
            )
        }

    }
}
