package com.akomissarova.testmuseum.artcollectionslist.repository

import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState

interface ArtCollectionsRepository {
    suspend fun getUpdatedList(): ArtCollectionsListLoadingState
}
