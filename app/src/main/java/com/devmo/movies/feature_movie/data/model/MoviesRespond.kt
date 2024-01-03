package com.devmo.movies.feature_movie.data.model

data class MoviesRespond(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)