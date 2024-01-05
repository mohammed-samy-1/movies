package com.devmo.movies.feature_movie.domain.model

data class MovieItem(
    val id: Int,
    val title: String = "",
    val date: String = "unknown",
    val imageURL: String = ""
)
