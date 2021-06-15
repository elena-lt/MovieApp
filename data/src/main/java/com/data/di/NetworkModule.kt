package com.data.di

import com.core.repository.MovieRepository
import com.data.network.TmdbApiService
import com.data.other.Const
import com.data.repository.MovieRepositoryImp
import com.data.repository.MoviesSource
import com.data.repository.MoviesSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = Const.BASE_TMDB_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(logging).dns(Dns.SYSTEM).connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TmdbApiService = retrofit.create(TmdbApiService::class.java)

    @Provides
    @Singleton
    fun provideMoviesDataSource(apiService: TmdbApiService): MoviesSource = MoviesSourceImp(apiService)

    @Provides
    @Singleton
    fun provideMovieRepository(movieDataSource: MoviesSource): MovieRepository = MovieRepositoryImp(movieDataSource)

}