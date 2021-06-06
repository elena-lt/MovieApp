package com.data.models

import com.google.gson.annotations.SerializedName

data class MoviesApiResponse(
    var dates: Dates? = null,
    var page: Int = 0,
    @SerializedName("results")
    var movieList: List<MovieApiResponse>? = null,
    var total_pages: Int = 0,
    var total_results: Int = 0,
)

data class MovieApiResponse(
    var adult: Boolean = false,
    var backdrop_path: String? = null,
    var genre_ids: List<Int>? = null,
    var id: Int = 0,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Double? = 0.0,
    var poster_path: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var video: Boolean? = false,
    var vote_average: Double? = 0.0,
    var vote_count: Double? = 0.0
)

class Dates {
    var maximum: String? = null
    var minimum: String? = null
}


