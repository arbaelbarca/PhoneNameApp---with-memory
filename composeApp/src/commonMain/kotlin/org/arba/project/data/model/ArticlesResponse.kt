package org.arba.project.data.model

import org.arba.project.data.mapping.ArticlesMapping
import kotlin.random.Random



val articlesDummy: List<ArticlesMapping.Article> = listOf(
    ArticlesMapping.Article(
        source = ArticlesMapping.Article.Source("My news"),
        author = "The author",
        title = "etBlue fined \$2 million for pattern of ‘chronically delayed’ flights - The Washington Post",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticlesMapping.Article(
        source = ArticlesMapping.Article.Source("My news"),
        author = "The author",
        title = "vidia Leads Slew Of New Buys As Market Rebounds - Investor's Business Daily",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticlesMapping.Article(
        source = ArticlesMapping.Article.Source("My news"),
        author = "The author",
        title = "tock market today: S&P 500 rallies to its first gain since Christmas - The Associated Press",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticlesMapping.Article(
        source = ArticlesMapping.Article.Source("My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    ),
    ArticlesMapping.Article(
        source = ArticlesMapping.Article.Source("My news"),
        author = "The author",
        title = "This is the main news title headline. This is the main news title headline.",
        description = "This is the main news description. This is the main news description. This is the main news description",
        url = "",
        urlToImage = "https://www.marketscreener.com/images/reuters/2024-03-05T144855Z_1_LYNXNPEK240IP_RTROPTP_3_GERMANY-TESLA-FIRE.JPG",
        publishedAt = Random(100).nextInt().toString(),
        content = "What is the content?"
    )
)
//val newsArticles = ArticlesResponse(
//    articlesDummy,
//    "dwe",
//    5
//)