package com.alviandf.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alviandf.moviecatalogue.data.source.local.entity.MovieEntity
import com.alviandf.moviecatalogue.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieAndTvShowDatabase : RoomDatabase() {
    abstract fun movieAndTvShowDao(): MovieAndTvShowDao

    companion object {

        @Volatile
        private var INSTANCE: MovieAndTvShowDatabase? = null

        fun getInstance(context: Context): MovieAndTvShowDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieAndTvShowDatabase::class.java,
                    "movieortvshow.db"
                ).build()
            }
    }
}