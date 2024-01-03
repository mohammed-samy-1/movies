package com.devmo.movies.feature_movie.pressentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.devmo.movies.feature_movie.domain.model.CastItem
import com.devmo.movies.feature_movie.domain.model.MovieDetails
import com.devmo.movies.feature_movie.pressentation.DetailsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(id: Int, onBackClicked: () -> Unit, viewModel: DetailsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        viewModel.getMovieDetails(id)
        val movieDetail = viewModel.movie.value
        TopAppBar(
            title = { Text(text = movieDetail.title) },
            navigationIcon = {
                IconButton(onClick = { onBackClicked() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        MovieDetailContent(movieDetail)
    }
}

@Composable
fun MovieDetailContent(movie: MovieDetails) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = movie.imageURL,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Name: ${movie.title}", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Year: ${movie.date}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Rating: ${movie.date}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Description: ${movie.overview}", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))



        if (movie.cast.isNotEmpty()) {
            Text(text = "Cast", style = MaterialTheme.typography.headlineMedium)
            LazyRow {
                items(movie.cast) { castMember ->
                    CastItem(item = castMember)
                }
            }
        }
    }
}


@Composable
fun CastItem(item: CastItem) {
    var height by remember {
        mutableStateOf(250.dp)
    }
    var width by remember {
        mutableStateOf(175.dp)
    }
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(height)
            .width(width),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.imageURL,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black,
                            )

                        )
                    )
                    .align(Alignment.BottomStart),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
            }

        }
    }
}

