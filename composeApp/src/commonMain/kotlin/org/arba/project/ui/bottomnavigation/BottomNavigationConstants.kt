package org.arba.project.ui.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import testkmp.composeapp.generated.resources.Res
import testkmp.composeapp.generated.resources.baseline_auto_delete_24


val bottomListItem = listOf(
    BottomNavigationItem(
        title = "Home",
        icon = Icons.Filled.Home,
        route = "Home"
    ),
    BottomNavigationItem(
        title = "Bookmark",
        icon = Icons.Filled.Favorite,
        route = "Bookmark"
    ),
)