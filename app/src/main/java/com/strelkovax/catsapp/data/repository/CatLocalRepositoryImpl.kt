package com.strelkovax.catsapp.data.repository

import com.strelkovax.catsapp.data.database.CatInfoDao
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CatLocalRepositoryImpl(private val catInfoDao: CatInfoDao) : CatLocalRepository {
    override suspend fun addCatToFavorite(catItem: CatItem) {
        withContext(Dispatchers.IO) {
            catInfoDao.insertCatItem(catItem)
        }
    }

    override suspend fun deleteCatFromFavorite(catItem: CatItem) {
        withContext(Dispatchers.IO) {
            catInfoDao.deleteCatItem(catItem)
        }
    }

    override suspend fun getCatItem(catItemId: String): Flow<CatItem>? {
        return catInfoDao.getInfoAboutCatById(catItemId)
    }

    override suspend fun getFavoriteCatList(): Flow<List<CatItem>> {
        return catInfoDao.getFavoriteCatList()
    }
}