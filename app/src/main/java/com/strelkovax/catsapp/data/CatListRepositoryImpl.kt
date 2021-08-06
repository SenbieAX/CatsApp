package com.strelkovax.catsapp.data

import androidx.lifecycle.LiveData
import com.strelkovax.catsapp.data.api.ApiFactory
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatListRepository

object CatListRepositoryImpl : CatListRepository {
    override fun addCatToFavorite(catItem: CatItem) {
        TODO("Not yet implemented")
    }

    override fun deleteCatFromFavorite(catItem: CatItem) {
        TODO("Not yet implemented")
    }

    override fun getCatItem(catItemId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getCatList(page: Int): List<CatItem> {
        return ApiFactory.apiService.getCatList(page = page)
    }

    override fun getFavoriteCatList(): LiveData<List<CatItem>> {
        TODO("Not yet implemented")
    }
}