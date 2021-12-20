package com.akomissarova.testmuseum.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akomissarova.testmuseum.repository.ArtCollectionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtCollectionsListViewModel(private val collectionsRepository: ArtCollectionsRepository) : ViewModel() {
    val data = collectionsRepository.list

    init {
        viewModelScope.launch(Dispatchers.IO) {
            collectionsRepository.refreshList()
        }
    }
}