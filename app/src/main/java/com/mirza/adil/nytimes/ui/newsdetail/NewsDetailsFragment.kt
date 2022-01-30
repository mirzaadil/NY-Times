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
import androidx.navigation.fragment.findNavController
import com.mirza.adil.nytimes.base.BaseFragment
import com.mirza.adil.nytimes.databinding.NewsDetailsFragmentBinding
import com.mirza.adil.nytimes.model.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment<NewsDetailsFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> NewsDetailsFragmentBinding
        get() = NewsDetailsFragmentBinding::inflate

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getArgsData()
        setupViews()
    }

    private fun getArgsData() {
        val news = arguments?.getParcelable<Result>("news")
        if (news == null) {
            findNavController().popBackStack()
            return
        }
    }

    private fun setupViews() {
    }

}
