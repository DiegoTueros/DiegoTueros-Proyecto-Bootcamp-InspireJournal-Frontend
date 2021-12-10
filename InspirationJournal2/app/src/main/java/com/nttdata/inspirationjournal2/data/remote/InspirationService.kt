package com.nttdata.inspirationjournal2.data.remote

import com.nttdata.inspirationjournal2.data.remote.request.InspirationRequest
import com.nttdata.inspirationjournal2.data.remote.response.AddInspirationResponse
import com.nttdata.inspirationjournal2.data.remote.response.InspirationDetailResponse
import com.nttdata.inspirationjournal2.data.remote.response.InspirationListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface InspirationService {
    @GET ("/inspiration")
    suspend fun getInspirationList(): Response<InspirationListResponse>

    @GET ("/inspiration/{id}")
    suspend fun getInspirationDetail(@Path("id") id : String): Response<InspirationDetailResponse>

    @GET ("/favorite")
    suspend fun getFavoriteList(): Response<InspirationListResponse>

    @POST ("/inspiration")
    suspend fun addInspiration(@Body addInspirationRequest: InspirationRequest): Response<AddInspirationResponse>
}