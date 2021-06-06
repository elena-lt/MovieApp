package com.data.models

import java.util.*

data class ReviewsApiResponse(

    val id: Int? = 0,
    val page: Int? = 0,
    val results: List<ReviewApiResponse>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)

data class AuthorDetails(
    val name: String? = null,
    val username: String? = null,
    val avatar_path: String? = null,
    val rating: Double? = 0.0
)

data class ReviewApiResponse(
    val author: String? = null,
    val author_details: AuthorDetails? = null,
    val content: String? = null,
    val created_at: Date,
    val id: String? = null,
    val updated_at: Date,
    val url: String? = null
)