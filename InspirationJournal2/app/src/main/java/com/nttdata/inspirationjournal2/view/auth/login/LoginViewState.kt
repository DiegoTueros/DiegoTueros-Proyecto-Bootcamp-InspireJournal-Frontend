package com.nttdata.inspirationjournal2.view.auth.login

import com.nttdata.inspirationjournal2.model.Auth

sealed class LoginViewState {
    object Loading: LoginViewState()
    data class  AuthSuccess(val auth: Auth): LoginViewState()
    object Error: LoginViewState()
}