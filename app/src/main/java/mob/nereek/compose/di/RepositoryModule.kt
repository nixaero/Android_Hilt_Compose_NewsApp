package mob.nereek.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mob.nereek.compose.data.repository.NewsRepositoryImpl
import mob.nereek.compose.domain.repository.NewsRepository


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // Provides an instance of NewsRepository interface
    @Provides
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository {
        return newsRepositoryImpl
    }

}