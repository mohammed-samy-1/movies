package com.devmo.movies.feature_movie.pressentation.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.devmo.movies.R
import com.devmo.movies.core.pressentation.theme.ui.*
import com.devmo.movies.feature_movie.data.model.Genre
import com.devmo.movies.feature_movie.domain.model.MovieItem
import com.devmo.movies.feature_movie.pressentation.MainViewModel


@ExperimentalFoundationApi
@Composable
fun HomeScreen(viewModel: MainViewModel, onMovieClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            viewModel.getALlMovies()

            Search {
                viewModel.search(it)
            }
            Chips(
                chips = viewModel.genres.value,
                onClick = {
                    viewModel.getALlMoviesByGenre(it)
                }
            )
            MoviesList(movies = viewModel.movies, onMovieClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(onClick: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var query by remember {
            mutableStateOf(TextFieldValue(""))
        }
        OutlinedTextField(
            value = query, onValueChange = { query = it },
            modifier = Modifier.weight(1f),
            singleLine = true,
            textStyle = Typography.titleSmall.copy(color = Color.White),
            shape = RoundedCornerShape(10.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "search", tint = Color.White, modifier = Modifier
                .size(50.dp)
                .padding(start = 8.dp)
                .clickable { onClick(query.text) }

        )
    }
}

@Composable
fun Chips(chips: List<Genre>, onClick: (Int) -> Unit) {
    var selected by remember {
        mutableIntStateOf(0)
    }
    LazyRow(Modifier.padding(10.dp)) {
        items(chips.size) {
            Box(

                modifier =
                Modifier
                    .padding(start = 15.dp, top = 15.dp, end = 15.dp)
                    .clickable {
                        onClick(chips[it].id)
                        selected = it
                    }
                    .clip(RoundedCornerShape(20))
                    .background(
                        if (selected == it) {
                            ButtonBlue
                        } else {
                            DarkerButtonBlue
                        }
                    )
                    .padding(15.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = chips[it].name, color = TextWhite)
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun MoviesList(movies: List<MovieItem>, onClick: (Int) -> Unit) {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 20.dp),
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 10.dp)
        ) {
            items(movies) {
                MovieItem(movie = it, onMovieClick = onClick)
            }
        }

    }

}

