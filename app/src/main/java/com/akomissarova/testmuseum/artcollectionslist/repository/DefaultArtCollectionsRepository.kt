package com.akomissarova.testmuseum.artcollectionslist.repository

import com.akomissarova.testmuseum.artcollectionslist.api.ArtCollectionsListApiService
import com.akomissarova.testmuseum.artcollectionslist.api.asDomainList
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState
import kotlinx.coroutines.withTimeout

const val TIMEOUT = 700L

class DefaultArtCollectionsRepository(private val apiServiceService: ArtCollectionsListApiService): ArtCollectionsRepository {

    override suspend fun getUpdatedList(): ArtCollectionsListLoadingState {
        //ideally there should also be network state monitoring
        //but due to time constraints it's left out
        return try {
            withTimeout(TIMEOUT) {
                val collection = apiServiceService.getNewestItemsList()
                if (collection.errorBody() == null) {
                    ArtCollectionsListLoadingState.Success(
                        collection.body()?.artList?.asDomainList() ?: listOf()
                    )
                } else {
                    ArtCollectionsListLoadingState.Error
                }
            }
        } catch (e: Exception) {
            ArtCollectionsListLoadingState.Error
        }
    }

}