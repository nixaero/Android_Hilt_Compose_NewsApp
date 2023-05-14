package mob.nereek.compose.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mob.nereek.compose.common.Constants
import mob.nereek.compose.common.Resource
import mob.nereek.compose.data.model.news.ArticleDto
import mob.nereek.compose.data.model.news.NewsResponse
import mob.nereek.compose.data.model.news.Source
import mob.nereek.compose.domain.model.Article
import mob.nereek.compose.domain.repository.NewsRepository
import mob.nereek.compose.domain.usecase.news.GetNewsListUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetNewsListUseCaseTest {

    private lateinit var newsRepository: NewsRepository
    private lateinit var getNewsListUseCase: GetNewsListUseCase
    private lateinit var article: Article
    private lateinit var articleDtoList: List<ArticleDto>
    private lateinit var articleList: List<Article>

    val newsResponseMock = mockk<Response<NewsResponse>>()

    @Before
    fun setup() {
        // Initialisation des variables et des objets nécessaires au test
        newsRepository = mockk()
        getNewsListUseCase = GetNewsListUseCase(newsRepository)

        // Classe modèle de la couche data
        articleDtoList = listOf(
            ArticleDto(
                author = "John Doe",
                content = "This is the content of article 1",
                description = "Description of article 1",
                publishedAt = "2023-05-12T10:00:00Z",
                source = Source(name = "Example News", id = "1"),
                title = "Article 1",
                url = "https://www.example.com/article1",
                urlToImage = "https://www.example.com/image1.jpg"
            ),
            ArticleDto(
                author = "Jane Smith",
                content = "This is the content of article 2",
                description = "Description of article 2",
                publishedAt = "2023-05-13T12:30:00Z",
                source = Source(name = "Another News Outlet", id = "2"),
                title = "Article 2",
                url = "https://www.example.com/article2",
                urlToImage = "https://www.example.com/image2.jpg"
            ),
        )

        // Classe modèle de la couche domaine
        articleList = listOf(
            Article(
                author = "John Doe",
                content = "This is the content of article 1",
                description = "Description of article 1",
                publishedAt = "2023-05-12T10:00:00Z",
                source = Source(name = "Example News", id = "1"),
                title = "Article 1",
                url = "https://www.example.com/article1",
                urlToImage = "https://www.example.com/image1.jpg"
            ),
            Article(
                author = "Jane Smith",
                content = "This is the content of article 2",
                description = "Description of article 2",
                publishedAt = "2023-05-13T12:30:00Z",
                source = Source(name = "Another News Outlet", id = "2"),
                title = "Article 2",
                url = "https://www.example.com/article2",
                urlToImage = "https://www.example.com/image2.jpg"
            ),
        )
    }

    @Test
    fun testGetNewsListUseCase() = runBlocking {
        // Définition des paramètres du test
        val token = Constants.API_NEWS_TOKEN
        val country = "us"

        // Configuration des comportements simulés pour les appels de méthodes
        coEvery { newsResponseMock.isSuccessful } returns true
        coEvery { runBlocking { newsResponseMock.body()?.articles } } returns articleDtoList
        coEvery { newsRepository.getNews(token, country) } returns newsResponseMock

        // Appel de la méthode à tester
        val result: Flow<Resource<List<Article>>> = getNewsListUseCase.invoke(token, country)

        // Collecte des résultats dans une liste mutable pour les vérifications ultérieures
        val resource = mutableListOf<Resource<List<Article>>>()
        launch {
            result.collect {
                resource.add(it)
            }
        }

        delay(1000) // Attente d'une seconde pour permettre la collecte des résultats

        // Vérification des résultats attendus
        assertEquals(Resource.Loading, resource.first()) // Vérifier la ressource de chargement
        assertEquals(Resource.Success(articleList), resource.last()) // Vérifier les données réussies
    }

}


