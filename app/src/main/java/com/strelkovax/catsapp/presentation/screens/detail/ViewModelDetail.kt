package com.strelkovax.catsapp.presentation.screens.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.strelkovax.catsapp.data.database.AppDatabase
import com.strelkovax.catsapp.data.repository.CatLocalRepositoryImpl
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.usecases.AddCatToFavoriteUseCase
import com.strelkovax.catsapp.domain.usecases.DeleteCatFromFavoriteUseCase
import com.strelkovax.catsapp.domain.usecases.GetCatItemUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class ViewModelDetail(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>()

    private val db = AppDatabase.getInstance(application)

    private val repository = CatLocalRepositoryImpl(db.catInfoDao())

    private val getFavoriteCat = GetCatItemUseCase(repository)
    private val addCatToFavorite = AddCatToFavoriteUseCase(repository)
    private val deleteCatFromFavorite = DeleteCatFromFavoriteUseCase(repository)

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    //Meh i don`t like it
    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean> get() = _back

    fun changeFavorite(catItem: CatItem) {
        viewModelScope.launch {
            val favoriteCat = getFavoriteCat.getCatItem(catItem.id)?.first()
            if (favoriteCat == null) {
                addCatToFavorite.addCatToFavorite(catItem)
                _text.value = "Удалить из избранного"
                _back.value = false
            } else {
                deleteCatFromFavorite.deleteCatFromFavorite(favoriteCat)
                _text.value = "Добавить в избранное"
                _back.value = true
            }
        }
    }

    fun initFavorite(catItem: CatItem) {
        viewModelScope.launch {
            val favoriteCat = getFavoriteCat.getCatItem(catItem.id)?.first()
            if (favoriteCat == null) {
                _text.value = "Добавить в избранное"
            } else {
                _text.value = "Удалить из избранного"
            }
        }
    }
}