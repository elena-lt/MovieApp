package com.movieapp.mappers

import com.movieapp.models.Movie
import com.movieapp.models.Movies

object MovieMapper {

    fun toMovies(list: com.core.models.Movies): Movies {
        return Movies(
            list.page,
            list.movieList?.map {
                toSingleMovie(it)
            },
            list.total_pages,
            list.total_results
        )
    }

    fun toSingleMovie(movie: com.core.models.Movie): Movie {
        return Movie(
            movie.adult,
            movie.backdrop_path,
            movie.genre_ids,
            movie.id,
            movie.original_language,
            movie.original_title,
            movie.overview,
            movie.popularity?.toFloat(),
            movie.poster_path,
            movie.release_date,
            movie.title,
            movie.video,
            movie.vote_average,
            movie.vote_count
        )
    }
}