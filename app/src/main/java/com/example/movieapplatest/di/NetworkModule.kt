package com.example.movieapplatest.di

import android.content.Context
import com.example.movieapplatest.data.remote.APIService
import com.example.movieapplatest.data.remote.NetworkManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.movieapplatest.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {

        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            val chunk = ChuckerInterceptor.Builder(get() as Context).build()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
            builder.addInterceptor(chunk)
        }
        return@single builder.build()
    }

    single {
        Retrofit.Builder()
            .client(get() as OkHttpClient)
//            .baseUrl(BuildConfig.BASE_URL)
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)
    }

    single { NetworkManager(get() as APIService) }

}

