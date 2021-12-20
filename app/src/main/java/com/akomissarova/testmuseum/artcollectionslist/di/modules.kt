package com.akomissarova.testmuseum.artcollectionslist.di

import com.akomissarova.testmuseum.artcollectionslist.api.ArtCollectionsListApiService
import com.akomissarova.testmuseum.artcollectionslist.network.AuthInterceptor
import com.akomissarova.testmuseum.artcollectionslist.repository.ArtCollectionsRepository
import com.akomissarova.testmuseum.artcollectionslist.repository.DefaultArtCollectionsRepository
import com.akomissarova.testmuseum.artcollectionslist.ui.ArtCollectionsListAdapter
import com.akomissarova.testmuseum.artcollectionslist.ui.viewmodel.ArtsCollectionsListViewModelFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val artCollectionsModule = module {

    single<ArtCollectionsRepository> { DefaultArtCollectionsRepository(get()) }

    single<ArtsCollectionsListViewModelFactory> { ArtsCollectionsListViewModelFactory(get()) }

    factory { ArtCollectionsListAdapter() }
}

val networkModule = module {
    factory { OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build() }
    factory { provideArtCollectionsApi(get()) }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://www.rijksmuseum.nl/")
            .client(get())
            .build()
    }
}

fun provideArtCollectionsApi(retrofit: Retrofit): ArtCollectionsListApiService =
    retrofit.create(ArtCollectionsListApiService::class.java)

