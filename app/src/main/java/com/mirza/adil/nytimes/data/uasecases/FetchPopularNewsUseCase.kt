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
package com.mirza.adil.nytimes.data.uasecases

import com.mirza.adil.nytimes.data.repository.NewsRepository

import javax.inject.Inject

/**
 * A use-case to load the popular photos from Unsplash API.
 * @author Mirza Adil
 */
class
FetchPopularNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke() = repository.getNews()
}
