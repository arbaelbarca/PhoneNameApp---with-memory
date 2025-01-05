package org.arba.project.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.arba.project.data.model.ArticlesResponse
import org.arba.project.data.model.articlesDummy
import org.arba.project.ui.state.UiStateApi

class ArticlesViewModel : ViewModel() {

    val mutableStateArticle =
        MutableStateFlow<UiStateApi<List<ArticlesResponse.Article>>>(UiStateApi.Idle)
    val stateArtciles: StateFlow<UiStateApi<List<ArticlesResponse.Article>>> = mutableStateArticle

    fun getArticlesNews() {
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