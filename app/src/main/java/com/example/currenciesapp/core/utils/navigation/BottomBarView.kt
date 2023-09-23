package com.example.currenciesapp.core.utils.navigation

import androidx.annotation.DrawableRes
import com.example.currenciesapp.features.main.screens.favorites.navigation.FavoritesRoute
import com.example.currenciesapp.features.main.screens.mainlist.navigation.MainRoute
import com.example.uikit.utills.Drawables

sealed class BottomBarView(
    val route: String,
    val name: String,
    @DrawableRes val iconRes: Int
) {
    object Main : BottomBarView(
        route = MainRoute.endpoint(),
        name = "Currencies",
        iconRes = Drawables.ic_main
    )

    object Favorites : BottomBarView(
        route = FavoritesRoute.endpoint(),
        name = "Favorites",
        iconRes = Drawables.ic_favorites
    )
}
