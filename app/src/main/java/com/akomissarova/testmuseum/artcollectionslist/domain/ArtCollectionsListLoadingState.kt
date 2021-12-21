package com.akomissarova.testmuseum.artcollectionslist.domain

sealed class ArtCollectionsListLoadingState {
    class Success(val result: List<ArtCollectionsListViewItem>) :
        ArtCollectionsListLoadingState()

    object Error : ArtCollectionsListLoadingState()
    object Progress : ArtCollectionsListLoadingState()
}