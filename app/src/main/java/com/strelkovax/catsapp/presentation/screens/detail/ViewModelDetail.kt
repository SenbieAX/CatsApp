package com.strelkovax.catsapp.presentation.screens.detail

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.strelkovax.catsapp.data.database.AppDatabase
import com.strelkovax.catsapp.data.repository.CatListRepositoryImpl
import com.strelkovax.catsapp.data.repository.CatLocalRepositoryImpl
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.usecases.AddCatToFavoriteUseCase
import com.strelkovax.catsapp.domain.usecases.DeleteCatFromFavoriteUseCase
import com.strelkovax.catsapp.domain.usecases.DownloadCatUseCase
import com.strelkovax.catsapp.domain.usecases.GetCatItemUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ViewModelDetail(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>()

    private val db = AppDatabase.getInstance(application)

    private val repositoryDB = CatLocalRepositoryImpl(db.catInfoDao())
    private val repository = CatListRepositoryImpl

    private val getFavoriteCat = GetCatItemUseCase(repositoryDB)
    private val addCatToFavorite = AddCatToFavoriteUseCase(repositoryDB)
    private val deleteCatFromFavorite = DeleteCatFromFavoriteUseCase(repositoryDB)
    private val downloadCat = DownloadCatUseCase(repository)

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text
    private val _errors = MutableLiveData<Throwable?>()
    val errors: MutableLiveData<Throwable?> get() = _errors
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

    fun downloadImage(fileName: String, url: String) {
        try {
            downloadCat.downloadCat(fileName, url, context)
        } catch (e: Exception) {
            _errors.value = Exception("Ошибка")
        }
    }

    fun clearErrors() {
        _errors.value = null
    }

    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        _errors.value = Exception("Нет соединения с интернетом")
        return false
    }
}