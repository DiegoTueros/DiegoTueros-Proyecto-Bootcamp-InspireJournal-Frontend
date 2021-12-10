package com.nttdata.inspirationjournal2.view.auth.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nttdata.inspirationjournal2.data.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel (
    private val authRepository: AuthRepository
) : ViewModel() {
    private val authResponse: RegisterViewModel? = null

    private val _viewState = MutableLiveData<RegisterViewState>()
    val viewState: LiveData<RegisterViewState> = _viewState

    fun register(username: String, email: String, password: String){

        viewModelScope.launch {
            try {
                if (authResponse == null ){

                    _viewState.value = RegisterViewState.Loading

                    val result = withContext(Dispatchers.IO){
                        authRepository.getAuthSignUp(username, email, password)
                    }

                    result?.let {
                        _viewState.value = RegisterViewState.AuthSuccess(it)
                    } ?: kotlin.run {
                        _viewState.value = RegisterViewState.Error
                    }
                }
            } catch (exception: Exception){
                exception.printStackTrace()
                _viewState.value = RegisterViewState.Error
            }
        }
    }

}