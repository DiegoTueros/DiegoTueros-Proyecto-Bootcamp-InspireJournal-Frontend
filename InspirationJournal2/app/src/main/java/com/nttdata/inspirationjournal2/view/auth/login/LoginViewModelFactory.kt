package com.nttdata.inspirationjournal2.view.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nttdata.inspirationjournal2.data.AuthRepository
import java.lang.IllegalArgumentException

class LoginViewModelFactory (
    val authRepository: AuthRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return  LoginViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}