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
package com.mirza.adil.nytimes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirza.adil.nytimes.databinding.NewsItemLayoutBinding
import com.mirza.adil.nytimes.model.Result

class NewsAdapter(val onNewsSelected: (news: Result, position: Int) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val photoItems: ArrayList<Result> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(photoItems[position], position)
    }

    override fun getItemCount() = photoItems.size

    fun updateItems(news: List<Result>) {
        photoItems.clear()
        photoItems.addAll(news)
    }

    inner class NewsViewHolder(private val itemBinding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(
            itemBinding.root
        ) {

        fun bind(news: Result, position: Int) {

            itemBinding.apply {
                tvTitle.text = news.title
                tvCreatedBy.text = news.byline
                tvSource.text = news.source
                tvDate.text = news.published_date
                image.setImageURI(
                    news.media?.get(0)?.mediaMetadata?.get(0)?.url
                )

                itemBinding.root.setOnClickListener {
                    onNewsSelected(news, position)
                }
            }


        }
    }
}
