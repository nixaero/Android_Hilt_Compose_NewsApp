package mob.nereek.compose.learning101


import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import mob.nereek.compose.common.Resource
import mob.nereek.compose.domain.model.Article
import mob.nereek.compose.domain.repository.NewsRepository
import mob.nereek.compose.domain.usecase.news.GetNewsListUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestingArea {

    private lateinit var newsRepository: NewsRepository
    private lateinit var getNewsListUseCase: GetNewsListUseCase

    @Before
    fun setUp() {
        newsRepository = mockk()
        getNewsListUseCase = GetNewsListUseCase(newsRepository)
    }

    @Test
    fun testLoadingState() = runBlocking {
        // Configurer le comportement du mock de newsRepository
        val token = "your_token"
        val country = "your_country"
        delay(1000)
        // Appeler la méthode invoke de GetNewsListUseCase
        val result: Flow<Resource<List<Article>>> = getNewsListUseCase.invoke(token, country)

        // Vérifier l'état de chargement
        val resource = result.first()
        delay(1000)
        assertEquals(Resource.Loading, resource)
    }
}
