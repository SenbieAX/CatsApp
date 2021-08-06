package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatListRepository

class DownloadCatUseCase(private val catListRepository: CatListRepository) {

    fun addCatToFavorite(catItem: CatItem) {
        catListRepository.addCatToFavorite(catItem)
    }
}