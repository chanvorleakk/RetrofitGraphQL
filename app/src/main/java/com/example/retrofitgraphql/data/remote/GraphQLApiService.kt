package com.example.retrofitgraphql.data.remote


import com.example.retrofitgraphql.base.GraphQLQuery
import com.example.retrofitgraphql.domain.model.LoginGraphQlResponse
import com.example.retrofitgraphql.domain.model.LogoutGraphQlResponse
import com.example.retrofitgraphql.domain.model.TagGraphQLResponse
import com.example.retrofitgraphql.domain.model.UserGraphQLResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GraphQLApiService {

    @POST("v1")
    fun fetchUserProfile(
        @Body request: GraphQLQuery
    ): Call<UserGraphQLResponse>

    @POST("v1")
    fun fetchProductionTag(
        @Body request: GraphQLQuery
    ): Call<TagGraphQLResponse>

    @POST("v1")
    fun postData(
        @Body request: GraphQLQuery
    ): Call<LoginGraphQlResponse>


    @POST("v1")
    fun logoutUser(
        @Body request: GraphQLQuery
    ): Call<LogoutGraphQlResponse>
}


