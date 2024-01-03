package com.devmo.movies.di

import com.devmo.movies.feature_movie.data.repository.RepositoryImpl
import com.devmo.movies.feature_movie.data.data_source.TmdbApi
import com.devmo.movies.feature_movie.domain.repository.Repository
import com.devmo.movies.feature_movie.domain.use_case.GetAllMovies
import com.devmo.movies.feature_movie.domain.use_case.GetGenres
import com.devmo.movies.feature_movie.domain.use_case.GetMovieDetails
import com.devmo.movies.feature_movie.domain.use_case.SearchMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val keyInterceptor = Interceptor{chain : Interceptor.Chain->
            var original = chain.request()
            val url: HttpUrl = original.url.newBuilder().addQueryParameter("api_key","6224d04e9e1e3894d834035308df11ef").build()
            original = original.newBuilder().url(url).build()
            chain.proceed(original)
        }

        val languageInterceptor = Interceptor{chain :Interceptor.Chain->
            var original = chain.request()
            var url: HttpUrl = original.url.newBuilder().addQueryParameter("language","en-US").build()
            original = original.newBuilder().url(url).build()
            chain.proceed(original)
        }


        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(keyInterceptor)
            .addInterceptor(languageInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                client
//                OkHttpClient.Builder()
//                    .addInterceptor { chain ->
//                        val request = chain.request().newBuilder()
//                            .addHeader("api_key", "6224d04e9e1e3894d834035308df11ef")
//                            .build()
//                        chain.proceed(request)
//                    }
//                    .build()

            )
            .build()
    }

    @Singleton
    @Provides
    fun provideTmdbApi(retrofit: Retrofit): TmdbApi {
        return retrofit.create(TmdbApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(api: TmdbApi): Repository {
        return RepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun getGenres(repo: Repository): GetGenres {
        return GetGenres(repo)
    }
    @Singleton
    @Provides
    fun getMovies(repo: Repository): GetAllMovies {
        return GetAllMovies(repo)
    }
    @Singleton
    @Provides
    fun searchMovies(repo: Repository): SearchMovies {
        return SearchMovies(repo)
    }
    @Singleton
    @Provides
    fun mocieDetails(repo: Repository): GetMovieDetails {
        return GetMovieDetails(repo)
    }


}