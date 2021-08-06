package com.strelkovax.catsapp.domain.repository

import androidx.lifecycle.LiveData
import com.strelkovax.catsapp.domain.entity.CatItem

interface CatListRepository {

    fun addCatToFavorite(catItem: CatItem)

    fun deleteCatFromFavorite(catItem: CatItem)

    fun getCatItem(catItemId: String)

    suspend fun getCatList(page: Int): List<CatItem>

    fun getFavoriteCatList(): LiveData<List<CatItem>>
}