package org.arba.project.ui.bottomnavigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.json.Json
import org.arba.project.data.mapping.ArticlesMapping
import org.arba.project.ui.page.bookmark.BookmarkScreen
import org.arba.project.ui.page.detail.ArticlesDetailScreen
import org.arba.project.ui.page.home.HomeScreen
import org.arba.project.ui.page.setting.SettingScreen
import org.arba.project.utils.AppConstants

@Composable
fun HomeNav() {
    val navController = rememberNavController()

    NavHostMain(
        navController = navController,
        onNavigate = { routeName ->
            navigateTo(routeName, navController)
        }
    )
}

@Composable
fun NavHostMain(
    navController: NavHostController = rememberNavController(),
    onNavigate: (rootName: String) -> Unit,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination

    Scaffold(
//        topBar = {
//            val title = getTitle(currentScreen)
//            TopBar(
//                title = title,
//                canNavigateBack = currentScreen?.route == AppScreen.Detail.route,
//                navigateUp = { navController.navigateUp() }
//            )
//        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            composable(route = BottomBarScreen.Home.route) {
                HomeScreen(navHostController = navController, onNavigate = onNavigate)
            }
            composable(route = BottomBarScreen.Bookmark.route) {
                BookmarkScreen(onNavigate = onNavigate)
            }
            composable(route = BottomBarScreen.ArticlesDetail.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<String>("article")
                    ?.let { article ->
                        val currentArticle: ArticlesMapping.Article = Json.decodeFromString(article)
                        ArticlesDetailScreen(navController = navController, currentArticle)
                    }
            }

            composable(route = BottomBarScreen.Setting.route) {
                SettingScreen(navController = navController)
            }

        }
    }
}

fun getTitle(currentScreen: NavDestination?): String {
    return when (currentScreen?.route) {
        BottomBarScreen.Home.route -> {
            "Home"
        }

        BottomBarScreen.Bookmark.route -> {
            "Bookmark"
        }

        else -> {
            ""
        }
    }
}

fun navigateTo(
    routeName: String,
    navController: NavController
) {
    when (routeName) {
        AppConstants.BACK_CLICK_ROUTE -> {
            navController.popBackStack()
        }

        else -> {
            navController.navigate(routeName)
        }
    }
}

sealed class AppScreen(val route: String) {
    data object Detail : AppScreen("nav_detail")
}

sealed class BottomBarScreen(
    val route: String,
    var title: String,
    val defaultIcon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = "HOME",
        title = "Home",
        defaultIcon = Icons.Filled.Home,
    )

    data object Bookmark : BottomBarScreen(
        route = "BOOKMARK",
        title = "Favorite",
        defaultIcon = Icons.Filled.Favorite,
    )

    data object ArticlesDetail : BottomBarScreen(
        route = "ArticlesDetail",
        title = "Detail",
        defaultIcon = Icons.Filled.Favorite,
    )

    data object Setting : BottomBarScreen(
        route = "Settings",
        title = "Settings",
        defaultIcon = Icons.Filled.Favorite,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back_button"
                    )
                }
            }
        }
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {
    val homeItem = BottomBarScreen.Home
    val bookmarkItem = BottomBarScreen.Bookmark

    val screens = listOf(
        homeItem,
        bookmarkItem
    )

//    AppBottomNavigationBar(show = navController.shouldShowBottomBar) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        screens.forEach { item ->
            NavigationBarItem(
                onClick = {
                    navigateBottomBar(navController, item.route)
                },
                selected = navController.currentBackStackEntry?.destination?.route == item.route,
                icon = {
                    Icon(
                        imageVector = item.defaultIcon,
                        contentDescription = "default"
                    )
                },
                label = {
                    Text(item.title)
                }
//                icon = item.defaultIcon,
//                label = item.title,
            )
//        }
        }
    }

}

@Composable
fun AppBottomNavigationBar(
    modifier: Modifier = Modifier,
    show: Boolean,
    content: @Composable (RowScope.() -> Unit),
) {
    Surface(
        modifier = modifier.windowInsetsPadding(BottomAppBarDefaults.windowInsets)
    ) {
        if (show) {
            Column {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = content
                )
            }
        }
    }
}

@Composable
fun RowScope.AppBottomNavigationBarItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    selected: Boolean,
) {
    Column(
        modifier = modifier
            .weight(1f)
            .clickable(
                onClick = onClick,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            imageVector = icon,
            contentDescription = icon.toString(),
            contentScale = ContentScale.Crop,
            colorFilter = if (selected) {
                ColorFilter.tint(MaterialTheme.colorScheme.primary)
            } else {
                ColorFilter.tint(MaterialTheme.colorScheme.outline)
            },
            modifier = modifier.then(
                Modifier.clickable {
                    onClick()
                }.size(24.dp)
            )
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            },
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
        )
    }
}

private fun navigateBottomBar(navController: NavController, destination: String) {
    navController.navigate(destination) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(BottomBarScreen.Home.route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private val NavController.shouldShowBottomBar
    get() = when (this.currentBackStackEntry?.destination?.route) {
        BottomBarScreen.Home.route,
        BottomBarScreen.Bookmark.route,
        BottomBarScreen.ArticlesDetail.route,
            -> true

        else -> false
    }