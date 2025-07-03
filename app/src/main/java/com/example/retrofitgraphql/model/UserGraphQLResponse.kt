package com.example.retrofitgraphql.model


data class UserGraphQLResponse(
    val data: UserProfileResponse
)

data class UserProfileResponse(
    val getProfile: UserProfile
)

data class UserProfile(
    val id: String,
    val fullName: String,
    val phoneNumber: String,
    val username: String
)