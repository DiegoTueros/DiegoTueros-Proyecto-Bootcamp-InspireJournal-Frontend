package com.nttdata.inspirationjournal2.data.remote.response

import com.google.gson.annotations.SerializedName

class AddInspirationResponse (
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
)
