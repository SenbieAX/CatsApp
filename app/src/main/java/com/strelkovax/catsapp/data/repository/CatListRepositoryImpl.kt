package com.strelkovax.catsapp.data.repository

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.strelkovax.catsapp.data.api.ApiFactory
import com.strelkovax.catsapp.domain.entity.CatItem
import com.strelkovax.catsapp.domain.repository.CatListRepository
import java.io.File


object CatListRepositoryImpl : CatListRepository {

    override suspend fun getCatList(page: Int): List<CatItem> {
        return ApiFactory.apiService.getCatList(page = page)
    }

    override fun downloadCat(fileName: String, url: String, context: Context) {
        val downloadManager: DownloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri: Uri = Uri.parse(url)
        val request = DownloadManager.Request(downloadUri)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setAllowedOverRoaming(false)
            .setTitle("cat-$fileName")
            .setMimeType("image/jpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                File.separator + fileName + ".jpg"
            )
        downloadManager.enqueue(request)
    }

}