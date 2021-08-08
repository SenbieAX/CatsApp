package com.strelkovax.catsapp.presentation.screens.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.strelkovax.catsapp.data.database.AppDatabase
import com.strelkovax.catsapp.data.repository.CatLocalRepositoryImpl
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.usecases.GetFavoriteCatListUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ViewModelFavoriteList(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>()

    private val db = AppDatabase.getInstance(application)

    private val repository = CatLocalRepositoryImpl(db.catInfoDao())

    private val getCatFavoriteCatList = GetFavoriteCatListUseCase(repository)

    private val _catsImgList = MutableLiveData<List<CatItem>>()
    val catsImgList: LiveData<List<CatItem>> get() = _catsImgList
    private val _errors = MutableLiveData<Throwable?>()
    val errors: MutableLiveData<Throwable?> get() = _errors

    fun loadSavedCats() {
        viewModelScope.launch {
            try {
                val data = getCatFavoriteCatList.getFavoriteCatList().first()
                _catsImgList.value = data
            } catch (e: Exception) {
                _errors.value = Exception("Ошибка")
            }
        }
    }

    fun clearErrors() {
        _errors.value = null
    }
}