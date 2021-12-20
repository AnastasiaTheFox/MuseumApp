package com.akomissarova.testmuseum.di

import com.akomissarova.testmuseum.repository.ArtCollectionsRepository
import com.akomissarova.testmuseum.repository.DefaultArtCollectionsRepository
import com.akomissarova.testmuseum.ui.ArtCollectionsListAdapter
import com.akomissarova.testmuseum.ui.ArtsCollectionsListViewModelFactory
import org.koin.dsl.module

val artCollectionsModule = module {

    single<ArtCollectionsRepository> { DefaultArtCollectionsRepository() }

    single<ArtsCollectionsListViewModelFactory> { ArtsCollectionsListViewModelFactory(get()) }

    factory { ArtCollectionsListAdapter() }
}