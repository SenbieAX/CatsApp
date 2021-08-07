package com.strelkovax.catsapp.domain.repository

import com.strelkovax.catsapp.domain.entity.CatItem
import kotlinx.coroutines.flow.Flow

interface CatLocalRepository {

    suspend fun addCatToFavorite(catItem: CatItem)

    suspend fun deleteCatFromFavorite(catItem: CatItem)

    suspend fun getCatItem(catItemId: String): Flow<CatItem>

    suspend fun getFavoriteCatList(): Flow<List<CatItem>>

}