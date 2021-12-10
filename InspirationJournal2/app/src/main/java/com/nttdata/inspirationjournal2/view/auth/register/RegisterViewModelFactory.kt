package com.nttdata.inspirationjournal2.view.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nttdata.inspirationjournal2.data.AuthRepository
import java.lang.IllegalArgumentException

class RegisterViewModelFactory (
    val authRepository: AuthRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return  RegisterViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}