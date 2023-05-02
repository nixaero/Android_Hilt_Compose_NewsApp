package mob.nereek.compose.domain.usecase.news

import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import mob.nereek.compose.common.Resource
import mob.nereek.compose.domain.model.Article
import mob.nereek.compose.data.model.news.toArticle
import mob.nereek.compose.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(token: String, country: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading()) // emit loading state

            // call the getTopHeadlines API method
            val newsResponse = newsRepository.getNews(token,country)

            if (newsResponse.isSuccessful) { // check if the response is successful
                val articles = newsResponse.body()?.articles?.map { it.toArticle().apply {

                    // remplace les valeurs vides par "N/A"
                            content = content.takeIf { it?.isNotEmpty() == true } ?: "No Description Available"

                            author = author.takeIf { it?.isNotEmpty() == true } ?: "No Author Available"

                            title = title.takeIf { it?.isNotEmpty() == true } ?: "No Title Available"
                    }
                }
                if (articles != null) {
                    emit(Resource.Success(articles)) // emit success state with filtered articles
                } else {
                    emit(Resource.Error("Error filtering articles"))
                }
            } else {
                emit(Resource.Error("Error fetching news")) // emit error state
            }
        }.catch { e ->
            emit(Resource.Error(e.message.toString())) // catch and emit any exception as error state
        }.flowOn(Dispatchers.IO) // run the flow on the IO dispatcher
    }
}