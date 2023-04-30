package com.example.newsapp.ui.favorite

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val favoriteArticles: MutableLiveData<List<Article>> = MutableLiveData()

    fun getFavoriteArticles(): LiveData<List<Article>> {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteArticles.postValue(repository.getFavoriteArticles())
        }
        return favoriteArticles
    }
}