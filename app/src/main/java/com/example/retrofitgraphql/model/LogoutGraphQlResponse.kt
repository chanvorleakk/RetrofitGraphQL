package com.example.retrofitgraphql.model


data class LogoutGraphQlResponse(
    val data: LogoutResponse
)

data class LogoutResponse(
    val logout: Boolean
)