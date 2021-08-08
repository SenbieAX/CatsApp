package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatLocalRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteCatListUseCase(private val catLocalRepository: CatLocalRepository) {

    suspend fun getFavoriteCatList(): Flow<List<CatItem>> {
        return catLocalRepository.getFavoriteCatList()
    }
}