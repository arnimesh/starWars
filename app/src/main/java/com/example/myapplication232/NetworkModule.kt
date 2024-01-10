package com.example.myapplication232


import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

const val BASE_URL = "https://swapi.dev/api/"



@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindCountriesRepository(impl: CharacterRepositoryImpl): CharacterRepository

    companion object {
        @Provides
        fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        @Provides
        fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }.setLevel(
                HttpLoggingInterceptor.Level.BODY
            )

        @Provides
        fun providesCountriesService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)
    }

}