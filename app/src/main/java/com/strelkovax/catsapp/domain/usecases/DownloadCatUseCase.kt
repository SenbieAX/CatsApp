package com.strelkovax.catsapp.domain.usecases

import com.strelkovax.catsapp.domain.repository.CatListRepository

class DownloadCatUseCase(private val catListRepository: CatListRepository) {

    fun downloadCat(fileName: String, url: String) {
        catListRepository.downloadCat(fileName, url)
    }
}