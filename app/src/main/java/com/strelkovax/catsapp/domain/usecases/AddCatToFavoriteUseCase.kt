package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatLocalRepository

class AddCatToFavoriteUseCase(private val catLocalRepository: CatLocalRepository) {

    suspend fun addCatToFavorite(catItem: CatItem) {
        catLocalRepository.addCatToFavorite(catItem)
    }
}