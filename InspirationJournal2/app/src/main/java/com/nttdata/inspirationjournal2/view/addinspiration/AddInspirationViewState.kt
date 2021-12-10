package com.nttdata.inspirationjournal2.view.addinspiration

sealed class AddInspirationViewState {
    object Loading: AddInspirationViewState()
    data class AddInspirationSucces(val status: Boolean): AddInspirationViewState()
    object Error: AddInspirationViewState()

}