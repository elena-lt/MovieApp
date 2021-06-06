package com.data.mappers

import com.core.models.Movie
import com.core.models.Movies
import com.data.models.MovieApiResponse
import com.data.models.MoviesApiResponse

object MoviesResponseMapper {
    fun toMovies(movies: MoviesApiResponse): Movies {
        return Movies(
            movies.page,
            movies.movieList?.map { toSingleMovie(it) },
            movies.total_pages, movies.total_results
        )
    }

    fun toSingleMovie(movie: MovieApiResponse): Movie {

        return Movie(
            movie.adult,
            movie.backdrop_path,
            movie.genre_ids,
            movie.id,
            movie.original_language,
            movie.original_title,
            movie.overview,
            movie.popularity,
            movie.poster_path,
            movie.release_date,
            movie.title,
            movie.video,
            movie.vote_average,
            movie.vote_count
        )

    }
}