package org.arba.project.ui.page.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.arba.project.ui.bottomnavigation.AppScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
//    var currentRoute by remember { mutableStateOf(bottomListItem[0].route) }
    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = "Articles News",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Setting"
                        )
                    }
                }
            )
        }
//        bottomBar = {
//            BottomNavigationBar(
//                bottomNavList = bottomListItem,
//                currentRoute = currentRoute,
//                onClickItemBar = {
//                    currentRoute = it.route
//                }
//            )
//        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Hello Articles")
        }
    }


}

@Composable
fun HomeView(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home")

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Go to Detail screen",
            modifier = Modifier.clickable {
                onNavigate(AppScreen.Detail.route)
            }
        )
    }
}
