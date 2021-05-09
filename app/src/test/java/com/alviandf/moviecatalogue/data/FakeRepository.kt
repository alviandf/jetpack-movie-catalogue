package com.alviandf.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alviandf.moviecatalogue.data.source.local.LocalDataSource
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity
import com.alviandf.moviecatalogue.data.source.remote.RemoteDataSource
import com.alviandf.moviecatalogue.data.source.remote.RemoteDataSource.LoadMoviesAndTvShowsCallback
import com.alviandf.moviecatalogue.data.source.remote.RemoteDataSource.LoadMoviesCallback
import com.alviandf.moviecatalogue.data.source.remote.RemoteDataSource.LoadTvShowsCallback
import com.alviandf.moviecatalogue.model.MovieOrTvShowResponse
import com.alviandf.moviecatalogue.model.MovieOrTvShowResult
import com.alviandf.moviecatalogue.utils.AppExecutors
import com.alviandf.moviecatalogue.utils.Constants

class FakeRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DataSource {

    override fun getMovies(): LiveData<MovieOrTvShowResponse> {
        val moviesResults = MutableLiveData<MovieOrTvShowResponse>()
        remoteDataSource.getAllMovies(object : LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponse: MovieOrTvShowResponse) {
                for(i in 0..(moviesResponse.results?.size?.minus(1) ?: 0)){
                    moviesResponse.results?.get(i)?.type = Constants.TYPE_MOVIE
                }

                moviesResults.postValue(moviesResponse)
            }
        })

        return moviesResults
    }

    override fun getMovieOrTvShowsById(data: MovieOrTvShowResult): LiveData<MovieOrTvShowResult> {
        val movieOrTvShowResults = MutableLiveData<MovieOrTvShowResult>()
        remoteDataSource.getAllMoviesAndTvShows(object : LoadMoviesAndTvShowsCallback {
            override fun onAllMoviesAndTvShowsReceived(movies: MovieOrTvShowResponse, tvShows: MovieOrTvShowResponse) {
                var listMovieOrTvShow = listOf<MovieOrTvShowResult>()

                if(data.type == Constants.TYPE_MOVIE){
                    listMovieOrTvShow = movies.results ?: listOf()
                }else if(data.type == Constants.TYPE_TVSHOW){
                    listMovieOrTvShow = tvShows.results ?: listOf()
                }

                for (movieOrTvShow in listMovieOrTvShow){
                    if (movieOrTvShow.id == data.id){
                        movieOrTvShowResults.postValue(movieOrTvShow)
                        break
                    }
                }
            }
        })

        return movieOrTvShowResults
    }

    override fun getTvShows(): LiveData<MovieOrTvShowResponse> {
        val tvShowsResults = MutableLiveData<MovieOrTvShowResponse>()
        remoteDataSource.getAllTvShows(object : LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponse: MovieOrTvShowResponse) {
                for(i in 0..(tvShowResponse.results?.size?.minus(1) ?: 0)){
                    tvShowResponse.results?.get(i)?.type = Constants.TYPE_TVSHOW
                }

                tvShowsResults.postValue(tvShowResponse)
            }
        })

        return tvShowsResults
    }

    override fun insertMovie(movieEntity: MovieEntity) {
        appExecutors.diskIO().execute {
            localDataSource.insertMovie(movieEntity)
        }
    }

    override fun insertTvShow(tvShowEntity: TvShowEntity) {
        appExecutors.diskIO().execute {
            localDataSource.insertTvShow(tvShowEntity)
        }
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getListTvShows(), config).build()
    }

    override fun getMovieDetail(id: Int): LiveData<MovieEntity> {
        return localDataSource.getDetailMovie(id)
    }

    override fun getTvShowDetail(id: Int): LiveData<TvShowEntity> {
        return localDataSource.getDetailTvShow(id)
    }

    override fun deleteMovieById(id: Int) {
        appExecutors.diskIO().execute{
            localDataSource.deleteMovieById(id)
        }
    }

    override fun deleteTvShowById(id: Int) {
        appExecutors.diskIO().execute{
            localDataSource.deleteTvShowById(id)
        }
    }
}