package com.danshima.flickrsearch.di

import android.content.Context
import com.danshima.flickrsearch.network.SearchApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.flickr.com/services/rest/"
private const val CONNECTION_TIMEOUT: Long = 15

/**
 * Unused atm
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideSearchApi(@Named("REST") okHttpClient: OkHttpClient): SearchApi =
        initRetrofit(okHttpClient).create(SearchApi::class.java)


    @Provides
    @Named("REST")
    @Singleton
    internal fun provideRestHttpClient(
        context: Context,
        interceptors: MutableSet<Interceptor>,
        authenticator: Authenticator
    ) = OkHttpClient.Builder().apply {
        for (interceptor in interceptors) {
            addNetworkInterceptor(interceptor)
        }
        authenticator(authenticator)
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        cache(Cache(context.cacheDir, cacheSize.toLong()))

    }.build()

    @Provides
    @IntoSet
    @Singleton
    internal fun provideOkHttpLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

}

private fun initRetrofit(okHttpClient: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        ))
        .client(okHttpClient)
        .build()