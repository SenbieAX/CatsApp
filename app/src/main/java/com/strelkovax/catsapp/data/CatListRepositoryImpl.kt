package com.strelkovax.catsapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.strelkovax.catsapp.data.api.ApiFactory
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatListRepository
import kotlinx.coroutines.withTimeout

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
        val response = ApiFactory.apiService.getCatList(page = page)
        return response.body().orEmpty()
    }

    override fun getFavoriteCatList(): LiveData<List<CatItem>> {
        TODO("Not yet implemented")
    }
}