package com.example.newsapp.ui.main

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NewsRepository):  ViewModel(){

    val newsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsPage = 1

    init {
        getNews("us")
        getCategory("")
    }
    fun getNews(countryCode: String) =
        viewModelScope.launch {
            newsLiveData.postValue(Resource.Loading())
            val response = repository.getNews(countryCode = countryCode, pageNumber = newsPage)
            if (response.isSuccessful){
                response.body().let {res ->
                    newsLiveData.postValue(Resource.Success(res))
                }
            } else{
              newsLiveData.postValue(Resource.Error(message = response.message()))
            }

        }


    fun getCategory(category: String) =
        viewModelScope.launch {
            newsLiveData.postValue(Resource.Loading())
            val response = repository.getCategory(category = category, pageNumber = newsPage)
            if (response.isSuccessful){
                response.body().let {res ->
                    newsLiveData.postValue(Resource.Success(res))
                }
            } else{
                newsLiveData.postValue(Resource.Error(message = response.message()))
            }
        }

}