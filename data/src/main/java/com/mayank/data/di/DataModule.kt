package com.mayank.data.di

import com.mayank.data.BuildConfig
import com.mayank.data.api.CharacterService
import com.mayank.data.repository.CharacterRepositoryImpl
import com.mayank.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val OK_HTTP_TIMEOUT = 60L

    @Provides
    @Singleton
    fun provideCharacterRemote(characterRemoteImpl: CharacterRepositoryImpl): CharacterRepository {
        return characterRemoteImpl
    }

    @Provides
    @Singleton
    fun provideCharacterService(baseUrl: String): CharacterService {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder().apply {
                    if (BuildConfig.DEBUG)
                        addInterceptor(httpLoggingInterceptor.apply {
                            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                        })
                    connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
                    readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
                }.build()
            )
            .build()
            .create(CharacterService::class.java)
    }

}