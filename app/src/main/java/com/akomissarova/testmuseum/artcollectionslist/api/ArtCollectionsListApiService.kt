package com.akomissarova.testmuseum.artcollectionslist.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtCollectionsListApiService {

    @GET("/api/en/collection")
    suspend fun getNewestItemsList(
        @Query("type") type: String = "jewellery",
        @Query("ps") itemsCount: Int = 30,
        @Query("s") order: String = "achronologic"
    ): Response<ArtCollectionsList>
}
