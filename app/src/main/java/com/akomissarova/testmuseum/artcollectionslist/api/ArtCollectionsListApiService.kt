package com.akomissarova.testmuseum.artcollectionslist.api

import retrofit2.Response
import retrofit2.http.GET

interface ArtCollectionsListApiService {

    @GET("/api/en/collection")
    suspend fun getList(): Response<ArtCollectionsList>
}
