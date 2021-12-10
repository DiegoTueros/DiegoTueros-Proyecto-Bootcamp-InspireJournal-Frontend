package com.nttdata.inspirationjournal2.view.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nttdata.inspirationjournal2.data.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val authToken: LoginViewModel? = null

    private val _viewState = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState> = _viewState

    fun login(email: String, password: String){

        viewModelScope.launch {
            try {
                if(authToken == null) {
                    _viewState.value = LoginViewState.Loading
                    val result = withContext(Dispatchers.IO){
                        authRepository.getAuthentication(email, password)
                    }

                    result?.let {
                        _viewState.value = LoginViewState.AuthSuccess(it)
                    } ?: run{
                        _viewState.value = LoginViewState.Error
                    }
                }
            } catch (exception: Exception){
                exception.printStackTrace()
                _viewState.value = LoginViewState.Error
            }
        }
    }
}