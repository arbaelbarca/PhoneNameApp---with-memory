package org.arba.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.arba.project.data.mapping.ArticlesMapping
import org.arba.project.data.mapping.mappingArticles
import org.arba.project.data.model.ArticlesResponseNew
import org.arba.project.data.model.articlesDummy
import org.arba.project.data.repository.ArticlesRepository
import org.arba.project.ui.state.UiStateApi

class ArticlesViewModel(
    val articlesRepository: ArticlesRepository
) : ViewModel() {

    val mutableStateArticle =
        MutableStateFlow<UiStateApi<List<ArticlesMapping.Article>>>(UiStateApi.Idle)
    val stateArtciles: StateFlow<UiStateApi<List<ArticlesMapping.Article>>> =
        mutableStateArticle

    fun getArticlesNews() {
        viewModelScope.launch {
            mutableStateArticle.emit(UiStateApi.Loading)
            try {
//                val getArticlesDummy = articlesDummy
                val responseHttp = articlesRepository.getArticlesApi()
                if (responseHttp.status.value in 200..299) {
                    val bodyResponseArticles = responseHttp.body<ArticlesResponseNew>()
                    val mappingArticles =
                        mappingArticles(bodyResponseArticles.articles.toMutableList())
                    mutableStateArticle.emit(UiStateApi.Success(mappingArticles))
                } else {
//                    val bodyResponseError = responseHttp.body<HttpResponse>()
                    mutableStateArticle.emit(UiStateApi.Error("error api"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                mutableStateArticle.emit(UiStateApi.Error(e.message.toString()))
            }
        }
    }

    fun searchArticlesNews(textSearch: String) {
        viewModelScope.launch {
            mutableStateArticle.emit(UiStateApi.Loading)
            try {
//                val getArticlesDummy = articlesDummy
                val responseHttp = articlesRepository.searchArticleApi(textSearch)
                if (responseHttp.status.value in 200..299) {
                    val bodyResponseArticles = responseHttp.body<ArticlesResponseNew>()
                    val mappingArticles =
                        mappingArticles(bodyResponseArticles.articles.toMutableList())
                    mutableStateArticle.emit(UiStateApi.Success(mappingArticles))
                } else {
//                    val bodyResponseError = responseHttp.body<HttpResponse>()
                    mutableStateArticle.emit(UiStateApi.Error("error api"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                mutableStateArticle.emit(UiStateApi.Error(e.message.toString()))
            }
        }
    }

    fun getArticlesNewsSearch(searchText: String) {
        viewModelScope.launch {
            mutableStateArticle.emit(UiStateApi.Loading)
            delay(1500)
            try {
                val getArticlesDummy = articlesDummy
                mutableStateArticle.emit(UiStateApi.Success(getArticlesDummy))
            } catch (e: Exception) {
                e.printStackTrace()
                mutableStateArticle.emit(UiStateApi.Error(e.message.toString()))
            }
        }
    }
}