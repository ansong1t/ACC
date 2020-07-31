package com.appetizercodingchallenge.data.services

import com.appetizercodingchallenge.data.responses.BaseResponse
import com.appetizercodingchallenge.data.responses.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {
    companion object {
        private const val DEFAULT_MEDIA_TYPE = "movie"
        private const val DEFAULT_COUNTRY = "au"
        private const val SEARCH_DEFAULT_LIMIT = 25
        private const val SEARCH_DEFAULT_LANG = "en_us"
        private const val SEARCH_DEFAULT_EXPLICIT = "no"
    }

    @GET("search")
    suspend fun search(
        @Query("term") term: String,
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("media") media: String = DEFAULT_MEDIA_TYPE,
        @Query("entity") entity: String = "",
        @Query("attribute") attribute: String = "",
        @Query("lang") lang: String = SEARCH_DEFAULT_LANG,
        @Query("limit") limit: Int = SEARCH_DEFAULT_LIMIT,
        @Query("explicit") explicit: String = SEARCH_DEFAULT_EXPLICIT
    ): Response<BaseResponse<List<ItemResponse>>>

    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    suspend fun getItems(): Response<BaseResponse<List<ItemResponse>>>
}
