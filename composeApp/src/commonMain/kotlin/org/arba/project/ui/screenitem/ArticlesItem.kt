package org.arba.project.ui.screenitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.AsyncImagePainter.*
import coil3.compose.AsyncImagePainter.State.*
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import org.arba.project.data.model.ArticlesResponse
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import testkmp.composeapp.generated.resources.Res
import testkmp.composeapp.generated.resources.compose_multiplatform

@Preview
@Composable
fun ArticlesItem(
    articlesResponse: ArticlesResponse.Article
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            contentColor = Color.White
        ),
        modifier = Modifier.padding(5.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val sizeResolver = rememberConstraintsSizeResolver()
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(articlesResponse.urlToImage)
                    .size(sizeResolver)
                    .build()
            )

            println("respon State ${painter.state}")

            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
//                painter = painter,
                model = articlesResponse.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(Res.drawable.compose_multiplatform)
            )

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
                    text = articlesResponse.description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = articlesResponse.author,
                    fontSize = 13.sp,
                    color = Color.Black
                )
            }

        }
    }


}