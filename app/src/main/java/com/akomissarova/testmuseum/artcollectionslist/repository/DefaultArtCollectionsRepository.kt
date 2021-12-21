package com.akomissarova.testmuseum.artcollectionslist.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.akomissarova.testmuseum.artcollectionslist.api.ArtCollectionsListApiService
import com.akomissarova.testmuseum.artcollectionslist.api.asDomainList
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState

class DefaultArtCollectionsRepository(private val apiServiceService: ArtCollectionsListApiService): ArtCollectionsRepository {

    override val list: MutableLiveData<ArtCollectionsListLoadingState> =
        MutableLiveData<ArtCollectionsListLoadingState>()

    override suspend fun refreshList() {
        //ideally there should also be network state monitoring
        try {
            list.postValue(ArtCollectionsListLoadingState.Progress)
            val collection = apiServiceService.getNewestItemsList()
            if (collection.errorBody() == null) {
                list.postValue(ArtCollectionsListLoadingState.Success(
                    collection.body()?.artList?.asDomainList() ?: listOf()
                ))
            } else {
                list.postValue(ArtCollectionsListLoadingState.Error)
            }
        } catch (e: Exception) {
            Log.e(DefaultArtCollectionsRepository::class.java.name, e.message.toString())
        }
    }

}