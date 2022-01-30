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
package com.mirza.adil.nytimes.data.repository

import androidx.annotation.WorkerThread
import com.mirza.adil.nytimes.data.DataState
import com.mirza.adil.nytimes.data.remote.*
import com.mirza.adil.nytimes.model.News
import com.mirza.adil.nytimes.utilies.StringUtils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * This is an implementation of [NewsRepository] to handle communication with [NewsApiService] server.
 * @author Mirza Adil
 */
class NewsRepositoryImpl @Inject constructor(
    private val stringUtils: StringUtils,
    private val apiService: NewsApiService
) : NewsRepository {


    @WorkerThread
    override suspend fun getNews(): Flow<DataState<News>> {
        return flow {
            apiService.getNews().apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
            }.onErrorSuspend {
                emit(DataState.error<News>(message()))
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<News>(stringUtils.noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<News>(stringUtils.somethingWentWrong()))
                }
            }
        }
    }
}






