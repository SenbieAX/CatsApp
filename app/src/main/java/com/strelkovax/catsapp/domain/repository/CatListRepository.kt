package com.strelkovax.catsapp.domain.repository

import androidx.lifecycle.LiveData
import com.strelkovax.catsapp.domain.entity.CatItem

interface CatListRepository {

    suspend fun getCatList(page: Int): List<CatItem>

    fun downloadCat(fileName: String, url: String)
}