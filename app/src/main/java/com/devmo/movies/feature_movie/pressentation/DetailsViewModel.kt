package com.devmo.movies.feature_movie.pressentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmo.movies.feature_movie.domain.model.MovieDetails
import com.devmo.movies.feature_movie.domain.use_case.GetMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovie :GetMovieDetails
): ViewModel() {
    private var _movie = mutableStateOf<MovieDetails>(MovieDetails())
    val movie get() = _movie
    fun getMovieDetails(id:Int) {
        viewModelScope.launch {
            getMovie(id).collectLatest {
                _movie.value = it
            }
        }
    }

}