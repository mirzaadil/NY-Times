package com.mirza.adil.nytimes.ui.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mirza.adil.nytimes.MainCoroutinesRule
import com.mirza.adil.nytimes.MockTestUtil
import com.mirza.adil.nytimes.data.DataState
import com.mirza.adil.nytimes.data.uasecases.FetchPopularNewsUseCase
import com.mirza.adil.nytimes.model.News
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsViewModelTest {
    private lateinit var viewModel: NewsViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @MockK
    lateinit var fetchPopularNewsUseCase: FetchPopularNewsUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test when HomeViewModel is initialized, news are fetched`() = runBlocking {
        // Given
        val givenNews = MockTestUtil.createNews()
        val uiObserver = mockk<Observer<HomeUiState>>(relaxed = true)
        val photosListObserver = mockk<Observer<News>>(relaxed = true)

        // When
        coEvery { fetchPopularNewsUseCase.invoke() }
            .returns(flowOf(DataState.success(givenNews)))

        // Invoke
        viewModel = NewsViewModel(fetchPopularNewsUseCase)
        viewModel.uiStateLiveData.observeForever(uiObserver)
        viewModel.newsListLiveData.observeForever(photosListObserver)

        // Then
        coVerify(exactly = 1) { fetchPopularNewsUseCase.invoke() }
        verify { uiObserver.onChanged(match { it == ContentState }) }
        verify { photosListObserver.onChanged(match { it == givenNews }) }
    }
}