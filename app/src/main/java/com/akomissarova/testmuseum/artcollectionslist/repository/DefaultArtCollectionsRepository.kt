package com.akomissarova.testmuseum.artcollectionslist.repository

import com.akomissarova.testmuseum.artcollectionslist.api.ArtCollectionsListApiService
import com.akomissarova.testmuseum.artcollectionslist.api.asDomainList
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState

class DefaultArtCollectionsRepository(private val apiServiceService: ArtCollectionsListApiService): ArtCollectionsRepository {

    override suspend fun getUpdatedList(): ArtCollectionsListLoadingState {
        //ideally there should also be network state monitoring
        return try {
            val collection = apiServiceService.getNewestItemsList()
            if (collection.errorBody() == null) {
                ArtCollectionsListLoadingState.Success(
                    collection.body()?.artList?.asDomainList() ?: listOf()
                )
            } else {
                ArtCollectionsListLoadingState.Error
            }
        } catch (e: Exception) {
            ArtCollectionsListLoadingState.Error
        }
    }

}