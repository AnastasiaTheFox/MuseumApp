package com.akomissarova.testmuseum.artcollectionslist.api

import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListViewItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtCollectionsList(
    @field:Json(name = "artObjects") val artList: List<ArtItem>
)

@JsonClass(generateAdapter = true)
data class ArtItem(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "webImage") val webImage: WebImage,
    @field:Json(name = "principalOrFirstMaker") val author: String
)

@JsonClass(generateAdapter = true)
data class WebImage(
    @field:Json(name = "url") val url: String
)

//possible improvement: using separate mapper classes
public fun List<ArtItem>.asDomainList(): List<ArtCollectionsListViewItem> =
    map {
        return@map ArtCollectionsListViewItem(it.title, it.author, it.webImage.url)
    }.toList()
