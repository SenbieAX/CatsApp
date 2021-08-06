package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatListRepository

class DeleteCatFromFavoriteUseCase(private val catListRepository: CatListRepository) {

    fun deleteCatFromFavorite(catItem: CatItem) {
        catListRepository.deleteCatFromFavorite(catItem)
    }
}