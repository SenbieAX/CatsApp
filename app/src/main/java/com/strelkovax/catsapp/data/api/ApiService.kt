package com.strelkovax.catsapp.data.api

import com.strelkovax.catsapp.domain.entity.CatItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("images/search")
    suspend fun getCatList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "685deef6-cb33-4382-bc98-5d343ddf3f23",
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_ORDER) order: String = "asc",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 20,
    ): List<CatItem>

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_ORDER = "order"
        private const val QUERY_PARAM_PAGE = "page"
    }
}