package org.arba.project.ui.state

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.Resource

sealed class UiStateApi<out T> {
    data object Idle : UiStateApi<Nothing>()
    data object Loading : UiStateApi<Nothing>()
    data class Success<T>(val data: T) : UiStateApi<T>()
    data class Error(val message: String) : UiStateApi<Nothing>()

    @Composable
    fun DisplayResult(
        onIdle: (@Composable () -> Unit)? = null,
        onLoading: @Composable () -> Unit,
        onSuccess: @Composable (T) -> Unit,
        onError: @Composable (String) -> Unit,
    ) {
        AnimatedContent(
            targetState = this,
            label = "Content Animation"
        ) { state ->
            when (state) {
                is Idle -> {
                    onIdle?.invoke()
                }

                is Loading -> {
                    onLoading()
                }

                is Success -> {
                    onSuccess(state.data)
                }

                is Error -> {
                    onError(state.message)
                }

                else -> {}
            }
        }
    }
}