package com.devmo.movies.feature_movie.pressentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmo.movies.feature_movie.data.model.Genre
import com.devmo.movies.feature_movie.data.model.Movie
import com.devmo.movies.feature_movie.domain.model.MovieItem
import com.devmo.movies.feature_movie.domain.use_case.GetAllMovies
import com.devmo.movies.feature_movie.domain.use_case.GetGenres
import com.devmo.movies.feature_movie.domain.use_case.SearchMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGenres: GetGenres,
    private val getMovies: GetAllMovies,
    private val searchMovies: SearchMovies
) : ViewModel() {
    val genres = mutableStateOf(
        listOf<Genre>()
    )
    private var selectedGenre: Int = -1
    val movies get() = _movies
    private var _movies = mutableStateListOf<MovieItem>()
    fun getAllGenres() {
        viewModelScope.launch {
            getGenres().collect() {
                val li = it.toMutableList()
                li.add(0, Genre(-1, "all"))
                genres.value = li.toList()
            }
        }
    }

    fun getALlMovies() {
        viewModelScope.launch {
            getMovies(-1, 1).collectLatest {
                _movies.clear()
                _movies.addAll(it)
            }
        }
    }

    fun getALlMoviesByGenre(genre: Int) {
        selectedGenre = genre
        viewModelScope.launch {
            getMovies(genre, 1).collectLatest {
                _movies.clear()
                _movies.addAll(it)
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            searchMovies(query, selectedGenre, 1).collectLatest {
                _movies.clear()
                _movies.addAll(it)
            }
        }
    }
}