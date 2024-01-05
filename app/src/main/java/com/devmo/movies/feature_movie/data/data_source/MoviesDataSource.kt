package com.devmo.movies.feature_movie.data.data_source


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devmo.movies.feature_movie.data.mapper.toMovieItem
import com.devmo.movies.feature_movie.domain.model.MovieItem
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(
    private val api: TmdbApi,
    private val genre: Int,
    private val query: String
) : PagingSource<Int, MovieItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        try {
            val page = params.key ?: 1
            val response = if (genre == -1) {
                if (query != "") {
                    api.searchMovies(query, page)
                } else
                    api.getMovies(page)
            } else if (query == "") {
                api.getMovies(genre, page)
            } else {
                api.searchMovies(query, genre, page)
            }
            val movies = response.results.map { it.toMovieItem() }

            return LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return null
    }
}