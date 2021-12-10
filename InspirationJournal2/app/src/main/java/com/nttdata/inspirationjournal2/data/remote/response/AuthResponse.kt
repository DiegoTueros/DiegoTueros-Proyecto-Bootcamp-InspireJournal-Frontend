package com.nttdata.inspirationjournal2.data.remote.response

import com.google.gson.annotations.SerializedName

class AuthResponse (
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: UserResponse,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
)

data class UserResponse(
    val username: String?
)