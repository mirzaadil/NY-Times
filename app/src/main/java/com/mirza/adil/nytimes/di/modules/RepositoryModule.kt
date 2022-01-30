
package com.mirza.adil.nytimes.di.modules

import android.app.Application
import com.mirza.adil.nytimes.data.remote.NewsApiService
import com.mirza.adil.nytimes.data.repository.NewsRepository
import com.mirza.adil.nytimes.data.repository.NewsRepositoryImpl
import com.mirza.adil.nytimes.utilies.StringUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 * @author Mirza Adil
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(stringUtils: StringUtils, apiService: NewsApiService): NewsRepository {
        return NewsRepositoryImpl(stringUtils, apiService)
    }
}
