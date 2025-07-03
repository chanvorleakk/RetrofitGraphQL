package com.example.retrofitgraphql.model


data class TagGraphQLResponse(
    val data:TagProductionResponse
)

data class TagProductionResponse(
    val getProductTags: TagResultList
)

data class TagResultList(
    val results: List<TagProduction>
)

data class TagProduction(
    val id: Int,
    val tagName: String
)
