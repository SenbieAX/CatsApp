package com.strelkovax.catsapp.presentation.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.strelkovax.catsapp.data.repository.CatListRepositoryImpl
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.usecases.GetCatListUseCase
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ViewModelList(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>()

    private val repository = CatListRepositoryImpl

    private val getCatListUseCase = GetCatListUseCase(repository)

    private val _catsImgList = MutableLiveData<List<CatItem>>()
    val catsImgList: LiveData<List<CatItem>> get() = _catsImgList
    private var _page = MutableLiveData(1)
    val page: LiveData<Int> get() = _page
    private val _errors = MutableLiveData<Throwable?>()
    val errors: MutableLiveData<Throwable?> get() = _errors

    fun loadData() {
        viewModelScope.launch {
            try {
                val data = getCatListUseCase.getCatList(_page.value ?: 1)
                _catsImgList.value = data
            } catch (e: UnknownHostException) {
                _errors.value = Exception("Нет соединения с интернетом")
            } catch (e: UnknownHostException) {
                _errors.value = Exception("Ошибка")
            }
        }
    }

    fun nextPage() {
        _page.value = _page.value?.plus(1)
        _page.value?.let { loadData() }
    }

    fun backPage() {
        if (_page.value != 1) {
            _page.value = _page.value?.minus(1)
            _page.value?.let { loadData() }
        }
    }

    fun clearErrors() {
        _errors.value = null
    }
}