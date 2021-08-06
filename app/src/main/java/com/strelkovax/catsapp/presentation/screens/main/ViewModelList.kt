package com.strelkovax.catsapp.presentation.screens.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.strelkovax.catsapp.data.CatListRepositoryImpl
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.usecases.GetCatListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelList : ViewModel() {

    private val repository = CatListRepositoryImpl

    private val getCatListUseCase = GetCatListUseCase(repository)

    private val _catsImgList = MutableLiveData<List<CatItem>>()
    val catsImgList: LiveData<List<CatItem>> get() = _catsImgList
    private var _page = MutableLiveData(1)
    val page: LiveData<Int> get() = _page

    fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = getCatListUseCase.getCatList(_page.value ?: 1)
                withContext(Dispatchers.Main) {
                    _catsImgList.value = (data.map { it })
                }
            } catch (e: Exception) {
                Log.d("TEST_TEST", e.toString())
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
}