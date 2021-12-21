package com.akomissarova.testmuseum.artcollectionslist.repository

import com.akomissarova.testmuseum.artcollectionslist.api.ArtCollectionsList
import com.akomissarova.testmuseum.artcollectionslist.api.ArtCollectionsListApiService
import com.akomissarova.testmuseum.artcollectionslist.api.ArtItem
import com.akomissarova.testmuseum.artcollectionslist.api.WebImage
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListLoadingState
import com.akomissarova.testmuseum.artcollectionslist.domain.ArtCollectionsListViewItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DefaultArtCollectionsRepositoryTest {

    private val apiService = mockk<ArtCollectionsListApiService>()
    private val response = mockk<Response<ArtCollectionsList>>()
    private val itemsList = mockk<ArtCollectionsList>()
    private val artList = listOf(
        ArtItem(
            id = "id1",
            title = "title1",
            webImage = WebImage("url1"),
            author = "author1"
        ),
        ArtItem(
            id = "id2",
            title = "title2",
            webImage = WebImage("url2"),
            author = "author2"
        ),
        ArtItem(
            id = "id3",
            title = "title3",
            webImage = WebImage("url3"),
            author = "author3"
        ),
        ArtItem(
            id = "id4",
            title = "title4",
            webImage = WebImage("url4"),
            author = "author4"
        )
    )

    private val domainArtList = listOf(
        ArtCollectionsListViewItem(
            title = "title1",
            author = "author1",
            imageUrl = "url1"
        ),
        ArtCollectionsListViewItem(
            title = "title2",
            author = "author2",
            imageUrl = "url2"
        ),
        ArtCollectionsListViewItem(
            title = "title3",
            author = "author3",
            imageUrl = "url3"
        ),
        ArtCollectionsListViewItem(
            title = "title4",
            author = "author4",
            imageUrl = "url4"
        )
    )
    private val repository = DefaultArtCollectionsRepository(apiService)

    @Before
    fun setUp() {
        coEvery { apiService.getNewestItemsList() } returns response
        every { itemsList.artList } returns artList
    }

    @Test
    fun `refresh list when successful result`() {
        runBlocking {
            every { response.body() } returns itemsList
            every { response.errorBody() } returns null
            val result = repository.getUpdatedList()
            assertTrue(result is ArtCollectionsListLoadingState.Success)
            assertEquals(domainArtList, (result as ArtCollectionsListLoadingState.Success).result)
        }
    }

    @Test
    fun `refresh list when error result`() {
        runBlocking {
            every { response.body() } returns null
            every { response.errorBody() } returns mockk<ResponseBody>()
            val result = repository.getUpdatedList()
            assertTrue(result is ArtCollectionsListLoadingState.Error)
        }
    }

    @Test
    fun `refresh list when exception occurs`() {
        runBlocking {
            coEvery { apiService.getNewestItemsList() } throws Exception()
            val result = repository.getUpdatedList()
            assertTrue(result is ArtCollectionsListLoadingState.Error)
        }
    }
}