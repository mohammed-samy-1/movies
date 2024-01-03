package com.devmo.movies.feature_movie.domain.use_case

import com.devmo.movies.feature_movie.data.model.Genre
import com.devmo.movies.feature_movie.data.model.Movie
import com.devmo.movies.feature_movie.domain.model.MovieDetails
import com.devmo.movies.feature_movie.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetails @Inject constructor(private val repo:Repository) {
    suspend operator fun invoke(id: Int): Flow<MovieDetails>{
        return repo.getMovieDetails(id)
    }
}