package com.shiqiliu.lowescoding.di

import com.shiqiliu.lowescoding.data.remote.ApiService
import com.shiqiliu.lowescoding.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DI {
    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        //add api_key as the query
        httpClient.addInterceptor { chain ->
            val origin = chain.request()
            val requestBuilder =
                origin.url.newBuilder().addQueryParameter("appid", Constants.apiKey).build()
            val request = origin.newBuilder().url(requestBuilder).build()
            chain.proceed(request)
        }
        httpClient.addNetworkInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}