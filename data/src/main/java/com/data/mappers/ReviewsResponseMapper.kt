package com.data.mappers

import com.core.models.AuthorDetails
import com.core.models.Reviews
import com.data.models.ReviewApiResponse
import com.data.models.ReviewsApiResponse

object ReviewsResponseMapper {

    fun toReviews(reviews: ReviewsApiResponse): Reviews {
        return Reviews(
            reviews.id,
            reviews.page,
            reviews.results?.map {
                toSingleResult(it)
            },
            reviews.total_pages, reviews.total_results
        )
    }

    fun toSingleResult(result: ReviewApiResponse): com.core.models.Review {
        return com.core.models.Review(
            result.author,
            AuthorDetails(
                result.author_details?.name,
                result.author_details?.username,
                result.author_details?.avatar_path,
                result.author_details?.rating
            ),
            result.content,
            result.created_at,
            result.id,
            result.updated_at,
            result.url
        )
    }
}