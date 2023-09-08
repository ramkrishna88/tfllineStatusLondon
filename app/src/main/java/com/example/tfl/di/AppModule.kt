package com.example.tfl.di

import com.example.tfl.data.api.*
import com.example.tfl.data.repository.*
import com.google.gson.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import okhttp3.*
import okhttp3.logging.*
import retrofit2.*
import retrofit2.converter.gson.*
import java.util.concurrent.*
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideHttpInterceptor() = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideTflApiService(okHttpClient: OkHttpClient, gson: Gson): TflApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.tfl.gov.uk/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(TflApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesTubeLineRepository(tubeLineRepositoryImpl: TubeLineRepositoryImpl): TubeLineRepository = tubeLineRepositoryImpl
}