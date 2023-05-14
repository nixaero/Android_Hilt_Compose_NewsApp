package mob.nereek.compose.viewmodel

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import mob.nereek.compose.common.Constants
import mob.nereek.compose.common.Resource
import mob.nereek.compose.data.model.news.Source
import mob.nereek.compose.domain.model.Article
import mob.nereek.compose.domain.usecase.news.GetNewsListUseCase
import mob.nereek.compose.presentation.screen.newslist.NewsListViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsListViewModelTest {
    // Use Mockk to create a mock of GetNewsListUseCase
    private val getNewsListUseCase = mockk<GetNewsListUseCase>()

    // Create an instance of NewsListViewModel, injecting the mock GetNewsListUseCase
    private val viewModel = NewsListViewModel(getNewsListUseCase)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getNews should update news StateFlow with Resource Loading`() {
        // Define the expected result
        val expected = Resource.Loading

        // Mock the behavior of GetNewsListUseCase to return a flow of Resource.Loading
        coEvery { getNewsListUseCase(Constants.API_NEWS_TOKEN, any()) } returns flowOf(expected)

        // Call the getNews() function
        viewModel.getNews()

        // Assert that the _news private MutableStateFlow was updated with the expected value
        assertEquals(expected, viewModel.news.value)
    }

    @Test
    fun `getNews should update news StateFlow with Resource Success`() {
        // Define the expected result
        val expected = Resource.Success(listOf(
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
        ))

        // Mock the behavior of GetNewsListUseCase to return a flow of Resource.Success
        coEvery { getNewsListUseCase(Constants.API_NEWS_TOKEN, any()) } returns flowOf(expected)

        // Call the getNews() function
        viewModel.getNews()

        // Assert that the _news private MutableStateFlow was updated with the expected value
        assertEquals(expected, viewModel.news.value)
    }

    @Test
    fun `getNews should update news StateFlow with Resource Error`() {
        // Define the expected result
        val expected = Resource.Error("An error occurred")

        // Mock the behavior of GetNewsListUseCase to return a flow of Resource.Error
        coEvery { getNewsListUseCase(Constants.API_NEWS_TOKEN, any()) } returns flowOf(expected)

        // Call the getNews() function
        viewModel.getNews()

        // Assert that the _news private MutableStateFlow was updated with the expected value
        assertEquals(expected, viewModel.news.value)
    }
}
