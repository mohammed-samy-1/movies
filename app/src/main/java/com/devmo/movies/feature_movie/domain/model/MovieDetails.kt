package com.devmo.movies.feature_movie.domain.model

import com.devmo.movies.feature_movie.data.model.Cast

data class MovieDetails(
    val id: Int = -1,
    val title: String = "",
    val date: String = "",
    val imageURL: String= "",
    val cast:List<CastItem> = listOf(),
    val overview: String = "",
    val rating: String = ""
)
