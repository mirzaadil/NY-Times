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
package com.mirza.adil.nytimes.ui.newsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mirza.adil.nytimes.base.BaseFragment
import com.mirza.adil.nytimes.databinding.NewsDetailsFragmentBinding
import com.mirza.adil.nytimes.model.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment<NewsDetailsFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> NewsDetailsFragmentBinding
        get() = NewsDetailsFragmentBinding::inflate


    private val viewModel: NewsDetailsViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val news = arguments?.getParcelable<Result>("news")
        if (news == null) {
            findNavController().popBackStack()
            return
        }
        setupViews()
        initObservations()
        viewModel.initNewsModel(news)

    }

    private fun setupViews() {
    }


    fun initObservations() {
        viewModel.photoModelLiveData.observe(viewLifecycleOwner) { news ->

            bi.titleDes.text = news.description
            bi.descriptionDes.text = news.title
            bi.createdByTitleDes.text = news.published_date
            bi.imageDetail.setImageURI(news.media?.get(0)?.mediaMetadata?.get(2)?.url)
        }

    }
}
