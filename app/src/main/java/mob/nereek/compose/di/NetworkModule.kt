package mob.nereek.compose.di

import android.content.Context
import android.net.ConnectivityManager
import com.chuckerteam.chucker.BuildConfig
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mob.nereek.compose.common.Constants
import mob.nereek.compose.common.utils.NetworkUtils
import mob.nereek.compose.data.remote.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
    Provides an OkHttpClient instance for making network requests, with interceptors
    for logging and error tracking.
    @param interceptor HttpLoggingInterceptor instance for logging network requests and responses
    @param context Application context
    @return An OkHttpClient instance
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .build()
    }

    /**

    Provides an HttpLoggingInterceptor instance for logging network requests and responses.
    @return An HttpLoggingInterceptor instance
     */
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    // Provides a Moshi instance for JSON serialization and deserialization
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /**
    Provides an instance of NewsApi using Retrofit and Moshi for network requests.
    @param moshi Moshi instance for JSON serialization and deserialization
    @param okHttpClient OkHttpClient instance for making network requests
    @return A NewsApi instance
     */
    @Provides
    @Singleton
    fun providesApiService(moshi: Moshi, okHttpClient: OkHttpClient): NewsApi = Retrofit
        .Builder()
        .run {
            baseUrl(Constants.BASE_URL)
            client(okHttpClient)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            build()
        }.create(NewsApi::class.java)

    // Provides a ConnectivityManager instance for checking network connectivity
    @Provides
    fun provideConnectivityManager(@ApplicationContext appContext: Context): ConnectivityManager {
        return appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    // Provides a NetworkUtils instance for checking network availability and type
    @Provides
    fun provideNetworkUtils(connectivityManager: ConnectivityManager): NetworkUtils {
        return NetworkUtils(connectivityManager)
    }

}