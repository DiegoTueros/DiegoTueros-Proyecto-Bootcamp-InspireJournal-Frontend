package com.nttdata.inspirationjournal2.view.auth.register

import com.nttdata.inspirationjournal2.model.Auth

sealed class RegisterViewState {
    object Loading: RegisterViewState()
    data class AuthSuccess(val auth: Auth): RegisterViewState()
    object Error: RegisterViewState()
}