/*
* Copyright 2022 Mirza Adil (https://www.linkedin.com/in/mirzaadil/)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.mirza.adil.nytimes.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mirza.adil.nytimes.R
import com.mirza.adil.nytimes.adapter.NewsAdapter
import com.mirza.adil.nytimes.base.BaseFragment
import com.mirza.adil.nytimes.databinding.NewsFragmentBinding
import com.mirza.adil.nytimes.utilies.gone
import com.mirza.adil.nytimes.utilies.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> NewsFragmentBinding
        get() = NewsFragmentBinding::inflate

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViews()
        initObservations()
    }

    private fun setUpViews() {
        newsAdapter = NewsAdapter { news, _ ->
            val bundle = bundleOf("news" to news)
            findNavController().navigate(
                R.id.action_homeFragment_to_photoDetailsFragment,
                bundle
            )
        }
        newsAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        bi.recyclerPopularPhotos.adapter = newsAdapter

    }


    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    bi.recyclerPopularPhotos.gone()
                    bi.progressPhotos.visible()
                }

                is ContentState -> {
                    bi.recyclerPopularPhotos.visible()
                    bi.progressPhotos.gone()
                }

                is ErrorState -> {
                    bi.progressPhotos.gone()
                }

            }
        }

        viewModel.newsListLiveData.observe(viewLifecycleOwner) { newsResults ->
            newsResults?.results?.let {
                newsAdapter.updateItems(it)
            }

        }
    }

}
