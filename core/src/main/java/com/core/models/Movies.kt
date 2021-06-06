package com.core.models

data class Movies(
    var page: Int = 0,
    var movieList: List<Movie>? = null,
    var total_pages: Int = 0,
    var total_results: Int = 0,
)

data class Movie(
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
