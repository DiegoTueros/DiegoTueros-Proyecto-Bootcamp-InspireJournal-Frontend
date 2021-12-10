package com.nttdata.inspirationjournal2.data.remote.response

import com.google.gson.annotations.SerializedName


data class InspirationListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("inspiration") val inspirations: List<InspirationItemResponse>
)

data class InspirationItemResponse(
    val _id: String,
    val title: String,
    val description: String?,
    val created_at: String,
    val URL: String?,
    val status: Boolean,
    //val category: String
    val category: CategoryResponse,
)

data class CategoryResponse(
    val _id: String,
    val name: String,
    val color_primary: String,
    val color_secondary: String,
    val icono: String
)