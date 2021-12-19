package com.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class MoviesApiResponse(
    @Json(name = "dates") var dates: Dates? = null,
    @Json(name = "page") var page: Int = 0,
    @Json(name = "results") var movieList: List<MovieApiResponse>? = null,
    @Json(name = "total_pages") var total_pages: Int = 0,
    @Json(name = "total_results") var total_results: Int = 0,
)

@JsonClass(generateAdapter = true)
data class MovieApiResponse(
    @Json(name = "adult") var adult: Boolean = false,
    @Json(name = "backdrop_path") var backdrop_path: String? = null,
    @Json(name = "genre_ids") var genre_ids: List<Int>? = null,
    @Json(name = "id") var id: Int = 0,
    @Json(name = "original_language") var original_language: String? = null,
    @Json(name = "original_title") var original_title: String? = null,
    @Json(name = "overview") var overview: String? = null,
    @Json(name = "popularity") var popularity: Double? = 0.0,
    @Json(name = "poster_path") var poster_path: String? = null,
    @Json(name = "release_date") var release_date: String? = null,
    @Json(name = "title") var title: String? = null,
    @Json(name = "video") var video: Boolean? = false,
    @Json(name = "vote_average") var vote_average: Double? = 0.0,
    @Json(name = "vote_count") var vote_count: Double? = 0.0
)

@JsonClass(generateAdapter = true)
class Dates {
    @Json(name = "maximum") var maximum: String? = null
    @Json(name = "minimum") var minimum: String? = null
}


