package com.example.retrofitgraphql.data.api

import android.content.Context
import com.example.retrofitgraphql.data.remote.GraphQLApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api-dev.jalatlogistics.com/service-user/"
    private var retrofit: Retrofit? = null

    fun getApi(context: Context): GraphQLApiService {
        if (retrofit == null) {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        return retrofit!!.create(GraphQLApiService::class.java)
    }
}

