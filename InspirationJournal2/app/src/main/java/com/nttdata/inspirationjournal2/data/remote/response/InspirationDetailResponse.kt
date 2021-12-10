package com.nttdata.inspirationjournal2.data.remote.response

import com.google.gson.annotations.SerializedName

data class InspirationDetailResponse (
    @SerializedName("count") val count: Int,
    @SerializedName("inspiration") val inspiration: InspirationItemResponse
)
