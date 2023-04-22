package com.example.newsapp.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile
        private var instantce: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instantce?: synchronized(LOCK) {
            instantce ?: createDatabase(context).also { instantce = it }
        }

        private fun createDatabase(context: Context): ArticleDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_database"
            ).build()
        }
    }
}