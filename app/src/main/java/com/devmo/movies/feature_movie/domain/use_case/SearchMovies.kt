package com.devmo.movies.feature_movie.domain.use_case

import androidx.paging.PagingData
import com.devmo.movies.feature_movie.domain.model.MovieItem
import com.devmo.movies.feature_movie.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovies @Inject constructor(private val repo:Repository) {
    suspend operator fun invoke(query:String, genre: Int): Flow<PagingData<MovieItem>> {
        return repo.searchMovies(query, genre)
    }
}