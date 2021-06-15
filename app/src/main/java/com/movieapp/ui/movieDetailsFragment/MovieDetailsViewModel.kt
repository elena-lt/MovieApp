package com.movieapp.ui.movieDetailsFragment

import androidx.lifecycle.*
import androidx.paging.map
import com.core.usecases.GetReviewsUsecase
import com.core.usecases.GetSimilarMoviesUsecase
import com.movieapp.mappers.MovieMapper
import com.movieapp.mappers.ReviewsMapper
import com.movieapp.models.Reviews
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getReviewsUsecase: GetReviewsUsecase,
    private val getSimilarMoviesUsecase: GetSimilarMoviesUsecase
) : ViewModel() {

    private val _reviews: MutableLiveData<Reviews> = MutableLiveData()
    val reviews: LiveData<Reviews> = _reviews

    private val _noReviewsFound: MutableLiveData<Boolean> = MutableLiveData()
    val noReviewsFound: LiveData<Boolean> = _noReviewsFound

    suspend fun getReviews(movieId: Int) =
         getReviewsUsecase.getReviews(movieId).map {
             it.map { review ->
                 ReviewsMapper.toSingleResult(review)
             }
         }

    suspend fun getSimilarMovies(movieId: Int) =
        getSimilarMoviesUsecase.load(movieId.toString()).map {
            it.map { movie ->
                MovieMapper.toSingleMovie(movie)
            }
        }

    companion object {
        const val TAG = "MovieDetailsViewModel"
    }
}