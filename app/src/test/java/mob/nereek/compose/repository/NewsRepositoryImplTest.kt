package mob.nereek.compose.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import mob.nereek.compose.data.model.news.NewsResponse
import mob.nereek.compose.data.remote.NewsApi
import mob.nereek.compose.data.repository.NewsRepositoryImpl
import mob.nereek.compose.domain.repository.NewsRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NewsRepositoryImplTest {

    private lateinit var newsApi: NewsApi
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setup() {
        newsApi = mockk()
        newsRepository = NewsRepositoryImpl(newsApi)
    }

    @Test
    fun `test getNews() should return response from newsApi`() = runBlocking {
        // Given
        val token = "your_token"
        val country = "your_country"
        val expectedResponse: Response<NewsResponse> = mockk()

        coEvery { newsApi.getTopHeadlines(token, country) } returns expectedResponse

        // When
        val result = newsRepository.getNews(token, country)

        // Then
        assertEquals(expectedResponse, result)
    }
}