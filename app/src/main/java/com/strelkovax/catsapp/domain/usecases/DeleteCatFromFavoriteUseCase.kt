package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatLocalRepository

class DeleteCatFromFavoriteUseCase(private val catLocalRepository: CatLocalRepository) {

    suspend fun deleteCatFromFavorite(catItem: CatItem) {
        catLocalRepository.deleteCatFromFavorite(catItem)
    }
}