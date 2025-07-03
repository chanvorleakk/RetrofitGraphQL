package com.example.retrofitgraphql.domain.model


data class LogoutGraphQlResponse(
    val data: LogoutResponse
)

data class LogoutResponse(
    val logout: Boolean
)