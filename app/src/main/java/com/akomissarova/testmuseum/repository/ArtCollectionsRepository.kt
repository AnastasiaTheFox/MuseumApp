package com.akomissarova.testmuseum.repository

import androidx.lifecycle.LiveData
import com.akomissarova.testmuseum.domain.ArtCollectionsListItem

interface ArtCollectionsRepository {
    val list: LiveData<List<ArtCollectionsListItem>>

    suspend fun refreshList()
}
