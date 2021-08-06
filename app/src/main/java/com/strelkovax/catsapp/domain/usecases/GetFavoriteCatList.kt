package com.strelkovax.catsapp.domain.usecases

import androidx.lifecycle.LiveData
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatListRepository

class GetFavoriteCatList(private val catListRepository: CatListRepository) {

    fun getFavoriteCatList(): LiveData<List<CatItem>> {
        return catListRepository.getFavoriteCatList()
    }
}