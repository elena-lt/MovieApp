package com.data.network

import com.data.BuildConfig
import com.data.models.MoviesApiResponse
import com.data.models.ReviewsApiResponse
import retrofit2.Response
import retrofit2.http.*

interface TmdbApiService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesApiResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesApiResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movie_id: String,
        @Query ("api_key") api_key: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): ReviewsApiResponse
}