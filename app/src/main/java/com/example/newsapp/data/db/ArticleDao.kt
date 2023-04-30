package com.example.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.newsapp.models.Article

@Dao
interface ArticleDao {

    @androidx.room.Query("SELECT * FROM articles")
    fun getAllArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    fun delete(article: Article)
}