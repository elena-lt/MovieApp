package com.movieapp.mappers

import com.core.models.Reviews
import com.movieapp.models.Review

object ReviewsMapper {

    fun toReviews(reviews: Reviews): com.movieapp.models.Reviews {
        return com.movieapp.models.Reviews(
            reviews.page,
            reviews.results?.map {
                toSingleResult(it)
            },
            reviews.total_pages, reviews.total_results
        )
    }

     fun toSingleResult(result: com.core.models.Review): Review {
        return Review(
            result.author,
            com.movieapp.models.AuthorDetails(
                result.author_details?.avatar_path,
                result.author_details?.rating
            ),
            result.content,
            result.created_at,
            result.id,
            result.updated_at,
        )
    }
}