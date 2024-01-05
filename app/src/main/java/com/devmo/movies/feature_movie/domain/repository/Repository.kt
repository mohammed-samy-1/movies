package com.devmo.movies.feature_movie.domain.repository

import androidx.paging.PagingData
import com.devmo.movies.feature_movie.data.model.Genre
import com.devmo.movies.feature_movie.data.model.Movie
import com.devmo.movies.feature_movie.domain.model.MovieDetails
import com.devmo.movies.feature_movie.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Repository {
    suspend fun getGenres(): Flow<List<Genre>>
    suspend fun searchMovies(query: String, genre: Int) : Flow<PagingData<MovieItem>>
    suspend fun getMovieDetails(id: Int) : Flow<MovieDetails>
    fun getMoviesPaging(genre: Int): Flow<PagingData<MovieItem>>
}