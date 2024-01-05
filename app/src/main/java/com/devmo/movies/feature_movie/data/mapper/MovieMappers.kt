package com.devmo.movies.feature_movie.data.mapper

import com.devmo.movies.feature_movie.data.model.Cast
import com.devmo.movies.feature_movie.data.model.DetailsResponse
import com.devmo.movies.feature_movie.data.model.Movie
import com.devmo.movies.feature_movie.domain.model.CastItem
import com.devmo.movies.feature_movie.domain.model.MovieDetails
import com.devmo.movies.feature_movie.domain.model.MovieItem

fun Movie.toMovieItem(): MovieItem {
    return MovieItem(
        this.id,
        this.title,
        (if ((this.release_date?.length ?: 0) >= 4) this.release_date?.substring(0, 4) else null)
            ?: "unknown",
        "https://image.tmdb.org/t/p/original/" + this.poster_path
    )
}

fun DetailsResponse.toMovieDetails(): MovieDetails {
    return MovieDetails(
        this.id,
        this.title,
        (if ((this.release_date?.length ?: 0) >= 4) this.release_date?.substring(0, 4) else null)
            ?: "unknown",
        "https://image.tmdb.org/t/p/original/" + this.poster_path,
        this.credits.cast.map { it.toCastItem() },
        this.overview,
        "%.2f/10".format(this.vote_average)
    )
}

fun Cast.toCastItem(): CastItem {
    return CastItem(
        name,
        "https://image.tmdb.org/t/p/original/$profile_path"
    )
}