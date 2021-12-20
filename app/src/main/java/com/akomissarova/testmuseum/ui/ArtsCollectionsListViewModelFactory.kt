package com.akomissarova.testmuseum.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akomissarova.testmuseum.repository.ArtCollectionsRepository

class ArtsCollectionsListViewModelFactory(
    private val collectionsRepository: ArtCollectionsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ArtCollectionsListViewModel(collectionsRepository) as T
    }
}