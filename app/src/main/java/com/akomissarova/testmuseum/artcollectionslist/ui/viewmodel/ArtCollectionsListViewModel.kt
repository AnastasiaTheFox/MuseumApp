package com.akomissarova.testmuseum.artcollectionslist.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState
import com.akomissarova.testmuseum.artcollectionslist.repository.ArtCollectionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class ArtCollectionsListViewModel(private val collectionsRepository: ArtCollectionsRepository) : ViewModel() {
    val items: MutableLiveData<ArtCollectionsListLoadingState> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            items.postValue(ArtCollectionsListLoadingState.Progress)
            items.postValue(collectionsRepository.getUpdatedList())
        }
    }
}