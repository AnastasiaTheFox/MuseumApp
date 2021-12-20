package com.akomissarova.testmuseum.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.akomissarova.testmuseum.domain.ArtCollectionsListItem

class DefaultArtCollectionsRepository: ArtCollectionsRepository {

    override val list: LiveData<List<ArtCollectionsListItem>> =
        MutableLiveData<List<ArtCollectionsListItem>>().apply{
            postValue(listOf(
                ArtCollectionsListItem("repo item 1"),
                ArtCollectionsListItem("repo item 2"),
                ArtCollectionsListItem("repo item 3"),
                ArtCollectionsListItem("repo item 4")
            ))
        }

    /*Transformations.map(database.collectionsDao.getList()) {
        it.asDomainModel()
    }*/

    override suspend fun refreshList() {
        try {
            //val collections = userListService.getRemoteList()
            //database.collectionsDao.insertAll(collections.asDatabaseModel())
        } catch (e: Exception) {
        }
    }

}