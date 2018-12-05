package com.andrew.associate.hellokotlin.model.api

import com.andrew.associate.hellokotlin.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {

    companion object {
        fun getAPI(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.URL +
                        "api/v1/json/${BuildConfig.API}")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}