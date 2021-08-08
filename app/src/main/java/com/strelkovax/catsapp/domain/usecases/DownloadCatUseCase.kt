package com.strelkovax.catsapp.domain.usecases

import android.content.Context
import com.strelkovax.catsapp.domain.repository.CatListRepository

class DownloadCatUseCase(private val catListRepository: CatListRepository) {

    fun downloadCat(fileName: String, url: String, context: Context) {
        catListRepository.downloadCat(fileName, url, context)
    }
}