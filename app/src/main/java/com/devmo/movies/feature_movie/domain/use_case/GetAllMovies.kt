package com.devmo.movies.feature_movie.domain.use_case

import com.devmo.movies.feature_movie.domain.repository.Repository
import java.util.concurrent.Flow
import javax.inject.Inject

class GetAllMovies @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(genre: Int, page:Int) = repository.getMovies(genre,page)
}