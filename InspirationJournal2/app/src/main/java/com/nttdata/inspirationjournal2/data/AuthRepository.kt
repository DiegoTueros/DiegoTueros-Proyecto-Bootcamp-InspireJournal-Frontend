package com.nttdata.inspirationjournal2.data

import android.util.Log
import com.nttdata.inspirationjournal2.data.remote.AuthService
import com.nttdata.inspirationjournal2.data.remote.request.AuthRequest
import com.nttdata.inspirationjournal2.data.remote.request.AuthRequestSignUp
import com.nttdata.inspirationjournal2.data.remote.toAuth
import com.nttdata.inspirationjournal2.model.Auth
import java.lang.Exception

class AuthRepository (
    private val authService: AuthService
){

    suspend fun getAuthentication(email:String, password: String): Auth{
        val request = AuthRequest(email, password)
        val response = authService.getAuth(request)
        if (response.isSuccessful){
            val authResponse = response.body()
            if (authResponse != null){
                    return authResponse.toAuth()
                }else{
                throw Exception("Auth response is null")
            }
        }
        else {
            throw Exception("Somenthig went wrong")
        }
    }

    suspend fun getAuthSignUp(username: String, email: String, password: String): Auth{
        val request = AuthRequestSignUp(username, email, password)

        val response = authService.getAuthSignUp(request)
        if(response.isSuccessful){
            val authResponse = response.body()
            if(authResponse != null){
                return authResponse.toAuth()
            }
            else{
                throw Exception("Auth response is null")
            }
        }
        else {
            throw Exception("Somenthig went wrong")
        }
    }
}