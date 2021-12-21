package com.akomissarova.testmuseum.artcollectionslist.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.akomissarova.testmuseum.artcollectionslist.api.ArtCollectionsListApiService
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListViewItem

class DefaultArtCollectionsRepository(private val apiServiceService: ArtCollectionsListApiService): ArtCollectionsRepository {

    override val list: MutableLiveData<ArtCollectionsListLoadingState> =
        MutableLiveData<ArtCollectionsListLoadingState>()

    override suspend fun refreshList() {
        //ideally there should also be network state monitoring
        try {
            list.postValue(ArtCollectionsListLoadingState.Progress)
            val collection = apiServiceService.getList()
            if (collection.errorBody() == null && collection.body() != null) {
                list.postValue(ArtCollectionsListLoadingState.Success(
                    collection.body()!!.artList.map {
                        return@map ArtCollectionsListViewItem(it.title, it.author, it.webImage.url)
                    }.toList()
                ))
            } else {
                list.postValue(ArtCollectionsListLoadingState.Error)
            }
        } catch (e: Exception) {
            Log.e(DefaultArtCollectionsRepository::class.java.name, e.message.toString())
        }
    }

}