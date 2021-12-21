package com.akomissarova.testmuseum.artcollectionslist.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtCollectionsListApiService {

    @GET("/api/en/collection")
    suspend fun getList(@Query("type") type: String = "jewellery"): Response<ArtCollectionsList>
}
