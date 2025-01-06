package org.arba.project.ui.screenitem

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arba.project.data.mapping.ArticlesMapping
import org.jetbrains.compose.resources.painterResource
import testkmp.composeapp.generated.resources.Res
import testkmp.composeapp.generated.resources.compose_multiplatform

@Composable
fun ArticlesItem(
    navHostController: NavHostController,
    articlesResponse: ArticlesMapping.Article,
    onClick: () -> Unit
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            contentColor = Color.White
        ),
        modifier = Modifier.padding(5.dp)
            .clickable {
                val articleStr = Json.encodeToString(articlesResponse)
                navHostController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("article", articleStr)
                }
                onClick()
            },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
//            val sizeResolver = rememberConstraintsSizeResolver()
//            val painter = rememberAsyncImagePainter(
//                model = ImageRequest.Builder(LocalPlatformContext.current)
//                    .data(articlesResponse.urlToImage)
//                    .size(sizeResolver)
//                    .build()
//            )

//            println("respon State ${painter.state}")
//            println("respon Url image ${articlesResponse.urlToImage}")

            val context = LocalPlatformContext.current
//            val imageLoader = ImageLoader.Builder(context)
//                .components {
//                    add(HttpImageLoader.Factory()) // Tambahkan dukungan HTTP Fetcher
//                }
//                .build()

//            AsyncImage(
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(Color.Gray),
////                painter = painter,
//                model = articlesResponse.urlToImage,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                onError = { println("respon error image ${it.result.throwable.message}") },
//                error = painterResource(Res.drawable.compose_multiplatform)
//
//            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(10)),
                contentAlignment = Alignment.Center
            ) {
                var imageLoadResult by remember {
                    mutableStateOf<Result<Painter>?>(null)
                }
                val painter = rememberAsyncImagePainter(
                    model = articlesResponse.urlToImage,
                    onSuccess = {
                        imageLoadResult =
                            if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                                Result.success(it.painter)
                            } else {
                                Result.failure(Exception("Invalid image size"))
                            }
                    },
                    onError = {
                        it.result.throwable.printStackTrace()
                        imageLoadResult = Result.failure(it.result.throwable)
                    }
                )

                val painterState by painter.state.collectAsState()
                val transition by animateFloatAsState(
                    targetValue = if (painterState is AsyncImagePainter.State.Success) {
                        1f
                    } else {
                        0f
                    },
                    animationSpec = tween(durationMillis = 800)
                )

                when (val result = imageLoadResult) {
                    null -> {
//                    PulseAnimation(
//                        modifier = Modifier.fillMaxSize()
//                    )
                    }

                    else -> {
                        Image(
                            painter = if (result.isSuccess) painter else {
                                painterResource(Res.drawable.compose_multiplatform)
                            },
                            contentDescription = articlesResponse.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    if (result.isSuccess) {
                                        rotationX = (1f - transition) * 30f
                                        val scale = 0.8f + (0.2f * transition)
                                        scaleX = scale
                                        scaleY = scale
                                    }
                                }
                        )
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = articlesResponse.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = articlesResponse.description.toString(),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2
                )

                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = articlesResponse.author.toString(),
                    fontSize = 13.sp,
                    color = Color.Black
                )
            }

        }
    }


}