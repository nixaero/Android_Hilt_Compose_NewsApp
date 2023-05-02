package mob.nereek.compose.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mob.nereek.compose.data.model.news.NewsResponse
import mob.nereek.compose.data.remote.NewsApi
import mob.nereek.compose.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
): NewsRepository {

    /**
     * This function retrieves the top headlines news from the NewsAPI endpoint
     * for a given country and authorization token.
     * @param token Authorization token to access the NewsAPI endpoint
     * @param country Country code for which to retrieve the news
     * @return A Retrofit response object containing the news response from the endpoint
     */
    override suspend fun getNews(token: String, country: String): Response<NewsResponse> {
        return withContext(Dispatchers.IO) {
            newsApi.getTopHeadlines(token, country)
        }
    }

}