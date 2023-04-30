package com.example.newsapp.ui.favorite

import androidx.lifecycle.*
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.data.db.ArticleDao
import com.example.newsapp.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: NewsRepository,
    private val articleDao: ArticleDao) : ViewModel() {

    val favoriteArticles: MutableLiveData<List<Article>> = MutableLiveData()

    fun getFavoriteArticles(): LiveData<List<Article>> {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteArticles.postValue(repository.getFavoriteArticles())
        }
        return favoriteArticles
    }

    fun deleteFromFavorite(article: Article) {
        viewModelScope.launch(Dispatchers.IO){
            articleDao.delete(article)
        }
    }
}