package com.akomissarova.testmuseum.artcollectionslist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akomissarova.testmuseum.artcollectionslist.repository.ArtCollectionsRepository
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