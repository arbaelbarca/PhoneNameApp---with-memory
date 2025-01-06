package org.arba.project.data.mapping

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arba.project.data.model.ArticlesResponseNew
import org.arba.project.data.model.ArticlesResponseNew.Article
import org.arba.project.data.model.ArticlesResponseNew.Article.Source

@Serializable
data class ArticlesMapping(
    @SerialName("articles")
    val articles: List<Article>,
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int
) {

    @Serializable
    data class Article(
        @SerialName("author")
        val author: String? = null,
        @SerialName("content")
        val content: String? = null,
        @SerialName("description")
        val description: String? = null,
        @SerialName("publishedAt")
        val publishedAt: String,
        @SerialName("source")
        val source: Source?,
        @SerialName("title")
        val title: String,
        @SerialName("url")
        val url: String,
        @SerialName("urlToImage")
        val urlToImage: String? = null
    ) {

        @Serializable
        data class Source(
//            @SerialName("id")
//            val id: String? = "0",
            @SerialName("name")
            val name: String
        )
    }
}

fun mappingArticles(listArticlesApi: MutableList<ArticlesResponseNew.Article>): MutableList<ArticlesMapping.Article> {
    val listMappingArticles: MutableList<ArticlesMapping.Article> = mutableListOf()
    listArticlesApi.forEach { dataItem ->
        listMappingArticles.addAll(
            listOf(
                ArticlesMapping.Article(
                    dataItem.author,
                    dataItem.content.toString(),
                    dataItem.description,
                    dataItem.publishedAt,
                    null,
                    dataItem.title,
                    dataItem.url,
                    dataItem.urlToImage,
                )
            )
        )
    }

    return listMappingArticles
}