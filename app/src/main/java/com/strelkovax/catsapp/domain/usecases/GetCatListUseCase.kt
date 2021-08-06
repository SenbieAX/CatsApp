package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatListRepository

class GetCatListUseCase(private val catListRepository: CatListRepository) {

    suspend fun getCatList(page: Int): List<CatItem> {
        return catListRepository.getCatList(page)
    }
}