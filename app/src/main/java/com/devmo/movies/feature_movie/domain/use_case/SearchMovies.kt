package com.devmo.movies.feature_movie.domain.use_case

import com.devmo.movies.feature_movie.data.model.Genre
import com.devmo.movies.feature_movie.data.model.Movie
import com.devmo.movies.feature_movie.domain.model.MovieItem
import com.devmo.movies.feature_movie.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovies @Inject constructor(private val repo:Repository) {
    suspend operator fun invoke(query:String, genre: Int, page: Int): Flow<List<MovieItem>>{
        return repo.searchMovies(query, genre, page)
    }
}