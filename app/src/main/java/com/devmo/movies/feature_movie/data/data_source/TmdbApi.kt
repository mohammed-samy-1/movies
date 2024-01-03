package com.devmo.movies.feature_movie.data.data_source

import com.devmo.movies.feature_movie.data.model.DetailsResponse
import com.devmo.movies.feature_movie.data.model.GenrasResponse
import com.devmo.movies.feature_movie.data.model.MoviesRespond
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {
    @GET("genre/movie/list")
    suspend fun getGenres(): GenrasResponse

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): MoviesRespond

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MoviesRespond

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") searchQuery: String,
        @Query("page") page: Int
    ): MoviesRespond

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") searchQuery: String,
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): MoviesRespond


    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): DetailsResponse


}