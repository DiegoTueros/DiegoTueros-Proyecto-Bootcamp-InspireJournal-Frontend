package com.nttdata.inspirationjournal2.data.remote.request

data class AuthRequest (
    val email: String,
    val password: String
    )

data class AuthRequestSignUp(
    val username: String,
    val email: String,
    val password: String
)