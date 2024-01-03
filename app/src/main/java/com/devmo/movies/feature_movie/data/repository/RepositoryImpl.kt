package com.devmo.movies.feature_movie.data.repository

import com.devmo.movies.feature_movie.data.mapper.toMovieDetails
import com.devmo.movies.feature_movie.data.mapper.toMovieItem
import com.devmo.movies.feature_movie.data.data_source.TmdbApi
import com.devmo.movies.feature_movie.data.model.Genre
import com.devmo.movies.feature_movie.domain.model.MovieDetails
import com.devmo.movies.feature_movie.domain.model.MovieItem
import com.devmo.movies.feature_movie.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: TmdbApi) : Repository {
    override suspend fun getGenres() = flow<List<Genre>> {
        try {
            val genres = api.getGenres()
            emit(genres.genres)
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovies(genre: Int, page: Int) = flow<List<MovieItem>> {
        try {
            val respond = if (genre == -1) {
                api.getMovies(1)

            }
            else api.getMovies(genre, page)
            emit(respond.results.map { it.toMovieItem() })
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)
    override suspend fun searchMovies(query: String ,genre: Int, page: Int) = flow<List<MovieItem>> {
        try {
            val respond = if (genre == -1) {
                api.searchMovies(query, page)
            }
            else api.searchMovies(query, genre, page)
            emit(respond.results.map { it.toMovieItem() })
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)
    override suspend fun getMovieDetails(id: Int) = flow<MovieDetails> {
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