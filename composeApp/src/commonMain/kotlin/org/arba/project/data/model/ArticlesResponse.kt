package org.arba.project.data.model

import kotlin.random.Random

data class ArticlesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) {
    data class Article(
        val author: String,
        val content: String,
        val description: String,
        val publishedAt: String,
        val source: Source,
        val title: String,
        val url: String,
        val urlToImage: String
    ) {
        data class Source(
            val id: String,
            val name: String
        )
    }
}

val articlesDummy: List<ArticlesResponse.Article> = listOf(
    ArticlesResponse.Article(
        source = ArticlesResponse.Article.Source("dwa", "My news"),
        author = "The author",
        title = "etBlue fined \$2 million for pattern of ‘chronically delayed’ flights - The Washington Post",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticlesResponse.Article(
        source = ArticlesResponse.Article.Source("dawdwa", "My news"),
        author = "The author",
        title = "vidia Leads Slew Of New Buys As Market Rebounds - Investor's Business Daily",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticlesResponse.Article(
        source = ArticlesResponse.Article.Source("dwakjyk", "My news"),
        author = "The author",
        title = "tock market today: S&P 500 rallies to its first gain since Christmas - The Associated Press",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticlesResponse.Article(
        source = ArticlesResponse.Article.Source("dwserfewa", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticlesResponse.Article(
        source = ArticlesResponse.Article.Source("dwserfewa", "My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    )
)
val newsArticles = ArticlesResponse(
    articlesDummy,
    "dwe",
    5
)