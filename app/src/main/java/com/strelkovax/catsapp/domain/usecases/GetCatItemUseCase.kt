package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.repository.CatListRepository

class GetCatItemUseCase(private val catListRepository: CatListRepository) {

    fun getCatItem(catItemId: String) {
        catListRepository.getCatItem(catItemId)
    }
}