package mob.nereek.compose.data.model.news

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import mob.nereek.compose.domain.model.Article

/**
 * Data transfer object representing an article retrieved from the news API.
 *
 * This class is annotated with Moshi's @JsonClass annotation to enable JSON parsing.
 */
@JsonClass(generateAdapter = true)
data class ArticleDto(
    @Json(name = "author")
    val author: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "publishedAt")
    val publishedAt: String?,
    @Json(name = "source")
    val source: Source?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "urlToImage")
    val urlToImage: String?
)

/**
 * Extension function to convert an ArticleDto to an Article domain model object.
 */
fun ArticleDto.toArticle() = Article(
    author = author.orEmpty(),
    description = description.orEmpty(),
    content = content.orEmpty(),
    publishedAt = publishedAt.orEmpty(),
    source = source,
    title = title.orEmpty(),
    url = url.orEmpty(),
    urlToImage = urlToImage.orEmpty(),
)
