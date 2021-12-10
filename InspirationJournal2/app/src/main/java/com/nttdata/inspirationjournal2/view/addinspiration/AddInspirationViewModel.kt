package com.nttdata.inspirationjournal2.view.addinspiration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nttdata.inspirationjournal2.data.InspirationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AddInspirationViewModel(
    private val inspirationRepository: InspirationRepository
) :ViewModel(){
    private val addResponse: AddInspirationViewModel? = null

    private val _viewState = MutableLiveData<AddInspirationViewState>()
    val viewState: LiveData<AddInspirationViewState> = _viewState

    fun saveInspiration(title: String, description: String, category: String){

        viewModelScope.launch {
            try {
                if (addResponse == null){
                    _viewState.value = AddInspirationViewState.Loading
                    val result = withContext(Dispatchers.IO){
                        inspirationRepository.addInspiration(title, description, category)
                    }

                    result?.let {
                        _viewState.value = AddInspirationViewState.AddInspirationSucces(it)
                    } ?: run {
                        _viewState.value = AddInspirationViewState.Error
                    }
                }
            } catch (exception: Exception){
                exception.printStackTrace()
                _viewState.value = AddInspirationViewState.Error
            }
        }
    }
}