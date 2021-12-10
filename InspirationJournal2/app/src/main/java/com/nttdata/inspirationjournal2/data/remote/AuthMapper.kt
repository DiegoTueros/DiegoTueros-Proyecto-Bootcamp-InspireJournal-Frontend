package com.nttdata.inspirationjournal2.data.remote

import com.nttdata.inspirationjournal2.data.remote.response.AuthResponse
import com.nttdata.inspirationjournal2.data.remote.response.UserResponse
import com.nttdata.inspirationjournal2.model.Auth
import com.nttdata.inspirationjournal2.model.User

fun AuthResponse.toAuth(): Auth{
    return Auth (
        token = token,
        message = message,
        user = user?.toUser(),
        status = status
    )
}

fun UserResponse.toUser(): User{
    return User (
        username = username
    )
}

