package mob.nereek.compose.data.remote

import mob.nereek.compose.common.Constants
import mob.nereek.compose.data.model.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    /**
     * Fetches the top headlines news articles from the News API for a specific country.
     *
     * @param apiKey the API key for accessing the News API
     * @param country the country for which to fetch top headlines news articles
     *
     * @return a [Response] object containing the top headlines news articles for the specified country
     */
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = Constants.API_NEWS_TOKEN,
        @Query("country") country: String
    ): Response<NewsResponse>

}
