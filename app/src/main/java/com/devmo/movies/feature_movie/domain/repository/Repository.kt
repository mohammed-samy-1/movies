package com.devmo.movies.feature_movie.domain.repository

import com.devmo.movies.feature_movie.data.model.Genre
import com.devmo.movies.feature_movie.data.model.Movie
import com.devmo.movies.feature_movie.domain.model.MovieDetails
import com.devmo.movies.feature_movie.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Repository {
    suspend fun getGenres(): Flow<List<Genre>>
    suspend fun getMovies(genre: Int, page:Int) : Flow<List<MovieItem>>
    suspend fun searchMovies(query: String, genre: Int, page: Int) : Flow<List<MovieItem>>
    suspend fun getMovieDetails(id: Int) : Flow<MovieDetails>
}