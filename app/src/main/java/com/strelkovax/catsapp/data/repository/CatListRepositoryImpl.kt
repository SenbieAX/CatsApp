package com.strelkovax.catsapp.data.repository

import com.strelkovax.catsapp.data.api.ApiFactory
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatListRepository

object CatListRepositoryImpl : CatListRepository {

    override suspend fun getCatList(page: Int): List<CatItem> {
        return ApiFactory.apiService.getCatList(page = page)
    }

    override fun downloadCat(fileName: String, url: String) {
        TODO("Not yet implemented")
    }

}