package com.example.retrofitgraphql.api


import com.example.retrofitgraphql.base.GraphQLQuery
import com.example.retrofitgraphql.model.LoginGraphQlResponse
import com.example.retrofitgraphql.model.LogoutGraphQlResponse
import com.example.retrofitgraphql.model.TagGraphQLResponse
import com.example.retrofitgraphql.model.UserGraphQLResponse

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


