package com.nttdata.inspirationjournal2.view.inspirationdetail

import android.util.Log
import androidx.lifecycle.*
import com.nttdata.inspirationjournal2.data.InspirationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class InspirationDetailViewModel(
    private val inspirationRepository: InspirationRepository
): ViewModel() {
    private val inspirationDetail: InspirationDetailViewModel? = null

    private val _viewState = MutableLiveData<InspirationDetailViewState>()
    val viewState: LiveData<InspirationDetailViewState> = _viewState

    fun loadInspirationDetail(inspirationId: String){
        viewModelScope.launch {
            try {
                if(inspirationDetail == null){
                    _viewState.value = InspirationDetailViewState.Loading

                    val result = withContext(Dispatchers.IO){
                        inspirationRepository.getInspirationDetail(inspirationId)
                    }

                    result?.let {
                        _viewState.value = InspirationDetailViewState.InspirationDetailSuccess(it)
                    } ?: run {
                        _viewState.value = InspirationDetailViewState.Error
                    }

                }
            } catch (exception: Exception){
                exception.printStackTrace()
                _viewState.value = InspirationDetailViewState.Error
            }


        }
    }

}