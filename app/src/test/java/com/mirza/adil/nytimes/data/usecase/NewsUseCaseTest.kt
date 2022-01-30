package com.mirza.adil.nytimes.data.usecase

import com.mirza.adil.nytimes.MockTestUtil
import com.mirza.adil.nytimes.data.DataState
import com.mirza.adil.nytimes.data.repository.NewsRepository
import com.mirza.adil.nytimes.data.uasecases.FetchPopularNewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsUseCaseTest {
    @MockK
    private lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking FetchNewsUseCase gives News`() = runBlocking {
        // Given
        val useCase = FetchPopularNewsUseCase(repository)
        val givenPhotos = MockTestUtil.createNews()

        // When
        coEvery { repository.getNews() }
            .returns(flowOf(DataState.success(givenPhotos)))

        // Invoke
        val newsFlow = useCase()

        // Then
        MatcherAssert.assertThat(newsFlow, CoreMatchers.notNullValue())

        val newsDataState = newsFlow.first()
        MatcherAssert.assertThat(newsDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(newsDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val news = (newsDataState as DataState.Success).data
        MatcherAssert.assertThat(news, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(news.results?.size, `is`(givenPhotos.results?.size))
    }
}