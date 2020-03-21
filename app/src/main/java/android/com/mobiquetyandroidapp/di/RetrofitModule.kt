package android.com.mobiquetyandroidapp.di

import android.com.mobiquetyandroidapp.BuildConfig
import android.com.mobiquetyandroidapp.repository.remote.api.MainApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single { getRetrofit(BuildConfig.API_URL, getOkHttpClient()).create(MainApi::class.java) }
}

private fun getRetrofit(url: String, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
}

private fun getOkHttpClient(): OkHttpClient = with(OkHttpClient.Builder()) {
    hostnameVerifier { _, _ -> true }
    kotlin.with(interceptors()) {
        add(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
    }

    build()
}