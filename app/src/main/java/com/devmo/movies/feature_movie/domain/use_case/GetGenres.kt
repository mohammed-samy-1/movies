package com.devmo.movies.feature_movie.domain.use_case

import com.devmo.movies.feature_movie.data.model.Genre
import com.devmo.movies.feature_movie.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenres @Inject constructor(private val repo:Repository) {
    suspend operator fun invoke(): Flow<List<Genre>>{
        return repo.getGenres()
    }
}