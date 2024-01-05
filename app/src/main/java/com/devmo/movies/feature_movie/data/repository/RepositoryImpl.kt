package com.devmo.movies.feature_movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devmo.movies.feature_movie.data.data_source.MoviesPagingSource
import com.devmo.movies.feature_movie.data.data_source.TmdbApi
import com.devmo.movies.feature_movie.data.mapper.toMovieDetails
import com.devmo.movies.feature_movie.domain.model.MovieItem
import com.devmo.movies.feature_movie.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: TmdbApi) : Repository {
    override suspend fun getGenres() = flow {
        try {
            val genres = api.getGenres()
            emit(genres.genres)
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    private val pageSize = 20

    override fun getMoviesPaging(genre: Int): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = { MoviesPagingSource(api, genre, "") }
        ).flow.flowOn(Dispatchers.IO)
    }
    override suspend fun searchMovies(query: String, genre: Int): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = { MoviesPagingSource(api, genre, query) }
        ).flow.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieDetails(id: Int) = flow {
        try {
            val response = api.getMovieDetails(id)
            emit(response.toMovieDetails())
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)



}