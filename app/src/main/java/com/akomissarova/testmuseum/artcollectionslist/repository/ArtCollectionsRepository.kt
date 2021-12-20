package com.akomissarova.testmuseum.artcollectionslist.repository

import androidx.lifecycle.LiveData
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState

interface ArtCollectionsRepository {
    val list: LiveData<ArtCollectionsListLoadingState>

    suspend fun refreshList()
}
