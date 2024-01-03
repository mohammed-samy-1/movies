package com.devmo.movies.feature_movie.pressentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devmo.movies.feature_movie.pressentation.components.HomeScreen
import com.devmo.movies.feature_movie.pressentation.components.MovieDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    val viewModel = hiltViewModel<MainViewModel>()
                    viewModel.getAllGenres()
                    HomeScreen(viewModel = viewModel, onMovieClick = { movieId ->
                        navController.navigate("movieDetails/$movieId")
                    })
                }
                composable("movieDetails/{movieId}") { backStackEntry ->
                    val movieId = backStackEntry.arguments?.getString("movieId")
                    if (movieId != null) {
                        MovieDetailScreen(
                            id = movieId.toInt(),
                            onBackClicked = {
                                navController.popBackStack()
                            },
                            viewModel = hiltViewModel<DetailsViewModel>()
                        )
                    }
                }
            }
        }
    }

}



