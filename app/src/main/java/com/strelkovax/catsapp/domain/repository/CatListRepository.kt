package com.strelkovax.catsapp.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.strelkovax.catsapp.domain.entity.CatItem

interface CatListRepository {

    suspend fun getCatList(page: Int): List<CatItem>

    fun downloadCat(fileName: String, url: String, context: Context)
}