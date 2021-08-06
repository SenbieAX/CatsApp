package com.strelkovax.catsapp.presentation.screens.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.strelkovax.catsapp.data.database.AppDatabase
import com.strelkovax.catsapp.domain.entity.CatItem

class ViewModelDetail(application: Application): ViewModel() {

    private val db = AppDatabase.getInstance(application)

    fun getFavoriteCatById(id: String): LiveData<CatItem> {
        return db.catInfoDao().getInfoAboutCatById(id)
    }
}