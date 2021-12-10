package com.nttdata.inspirationjournal2.data.remote

import com.nttdata.inspirationjournal2.data.remote.request.AuthRequest
import com.nttdata.inspirationjournal2.data.remote.request.AuthRequestSignUp
import com.nttdata.inspirationjournal2.data.remote.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST ("/auth/signin")
    suspend fun getAuth(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST ("/auth/signup")
    suspend fun getAuthSignUp(@Body authRequest: AuthRequestSignUp): Response<AuthResponse>

}