package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatLocalRepository
import kotlinx.coroutines.flow.Flow

class GetCatItemUseCase(private val catLocalRepository: CatLocalRepository) {

    suspend fun getCatItem(catItemId: String): Flow<CatItem> {
        return catLocalRepository.getCatItem(catItemId)
    }
}