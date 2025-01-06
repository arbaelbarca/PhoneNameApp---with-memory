package org.arba.project.ui.page.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arba.project.data.mapping.ArticlesMapping
import org.arba.project.data.model.ArticlesResponseNew
import org.arba.project.ui.bottomnavigation.AppScreen
import org.arba.project.ui.bottomnavigation.BottomBarScreen
import org.arba.project.ui.page.setting.component.effect.ShimmerEffect
import org.arba.project.ui.screenitem.ArticlesItem
import org.arba.project.utils.Type
import org.arba.project.utils.getType
import org.arba.project.viewmodel.ArticlesViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    onNavigate: (String) -> Unit
) {
//    var currentRoute by remember { mutableStateOf(bottomListItem[0].route) }

    val articlesViewModel = koinViewModel<ArticlesViewModel>()
    val stateArticlesViewModel = articlesViewModel.stateArtciles.collectAsState()

    var textInputSearch by rememberSaveable() {
        mutableStateOf("")
    }

    articlesViewModel.getArticlesNews()

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
                        navHostController.navigate(BottomBarScreen.Setting.route)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Setting"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            SearchBar(
                textSearch = textInputSearch,
                onValueChange = {
                    textInputSearch = it
                },
                onSearch = { query ->
                    if (query.trim().isNotEmpty()) {
                        articlesViewModel.searchArticlesNews(query)
                    }
                }
            )

            stateArticlesViewModel.value.DisplayResult(
                onIdle = {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = "Search Data Articles"
                        )
                    }
                },
                onLoading = {
//                    Box(
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        CircularProgressIndicator(
//                            modifier = Modifier
//                                .size(50.dp)
//                                .align(Alignment.Center)
//                        )
//                    }

                    ShimmerEffect()

                },
                onError = {
                    Text(
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "Erorr Data"
                    )
                },
                onSuccess = { artilesList ->
                    if (artilesList.isNotEmpty()) {
                        getArticlesDummy(
                            navHostController,
                            textInputSearch,
                            artilesList,
                            onClickItem = {
                                navHostController.navigate(BottomBarScreen.ArticlesDetail.route)
                            })
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = "Data Empty"
                            )
                        }

                    }
                }
            )
        }
    }

}

@Composable
fun getArticlesDummy(
    navHostController: NavHostController,
    textInputSearch: String,
    listArticle: List<ArticlesMapping.Article>,
    onClickItem: () -> Unit
) {

//    val filterArticles = listArticle.filter { itemArticle ->
//        itemArticle.title.contains(textInputSearch, true)
//    }

    val isDekstop = remember {
        getType() == Type.Dekstop
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDekstop) 3 else 1),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(listArticle) { item ->
            ArticlesItem(
                navHostController,
                item,
                onClick = onClickItem
            )
        }
    }
}

@Composable
fun SearchBar(
    textSearch: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = textSearch,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        },
        placeholder = {
            Text(text = "Search data")
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(textSearch)
            }
        )
    )
}
