package mob.nereek.compose.data.model.news

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import mob.nereek.compose.data.model.news.ArticleDto

/**

Data class representing the response received from the NewsAPI for a list of news articles.
@property articles the list of articles retrieved from the API
@property status the status of the API response
@property totalResults the total number of articles retrieved from the API
 */
@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "articles")
    val articles: List<ArticleDto>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "totalResults")
    val totalResults: Int?
)

