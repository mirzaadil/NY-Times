package com.mirza.adil.nytimes.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mirza.adil.nytimes.MainCoroutinesRule
import com.mirza.adil.nytimes.MockTestUtil
import com.mirza.adil.nytimes.data.DataState
import com.mirza.adil.nytimes.data.remote.NewsApiService
import com.mirza.adil.nytimes.data.remote.api.ApiUtil.successCall
import com.mirza.adil.nytimes.utilies.StringUtils
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsRepositoryTest {
    // Subject under test
    private lateinit var repository: NewsRepository
    @MockK
    private lateinit var apiService: NewsApiService

    @MockK
    private lateinit var stringUtils: StringUtils

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test getNews() gives data of news`() = runBlocking {
        // Given
        repository = NewsRepositoryImpl(stringUtils, apiService)
        val givenNews = MockTestUtil.createNews()
        val apiCall = successCall(givenNews)

        // When
        coEvery { apiService.getNews(any(), any()) }
            .returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.getNews()

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val newsDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(newsDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(newsDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val news = (newsDataState as DataState.Success).data
        MatcherAssert.assertThat(news, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(news.results?.size, CoreMatchers.`is`(MockTestUtil.createResult().size))

        coVerify(exactly = 1) { apiService.getNews(any(), any() ) }
        confirmVerified(apiService)
    }

}