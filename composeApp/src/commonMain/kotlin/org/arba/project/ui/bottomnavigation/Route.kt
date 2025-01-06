package org.arba.project.ui.bottomnavigation

sealed interface Route {

    data object Home : Route

    data object Bookmark : Route

    data object FavoriteDetail : Route

}