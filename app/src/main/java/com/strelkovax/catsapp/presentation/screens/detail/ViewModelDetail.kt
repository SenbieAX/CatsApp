package com.strelkovax.catsapp.presentation.screens.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.strelkovax.catsapp.data.database.AppDatabase
import com.strelkovax.catsapp.domain.entity.CatItem
import kotlinx.coroutines.flow.Flow

class ViewModelDetail(application: Application): ViewModel() {

    private val db = AppDatabase.getInstance(application)

    fun getFavoriteCatById(id: String): Flow<CatItem> {
        return db.catInfoDao().getInfoAboutCatById(id)
    }
}