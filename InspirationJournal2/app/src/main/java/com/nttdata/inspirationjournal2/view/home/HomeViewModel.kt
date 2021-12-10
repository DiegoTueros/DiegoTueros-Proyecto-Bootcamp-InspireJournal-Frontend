package com.nttdata.inspirationjournal2.view.home

import androidx.lifecycle.*
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nttdata.inspirationjournal2.data.InspirationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private  val inspirationRepository: InspirationRepository
): ViewModel() {
    private val inspirationList: HomeViewModel? = null

    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState> = _viewState

    fun loadInspirationList() {
        viewModelScope.launch() {

            if(inspirationList == null){
                _viewState.value = HomeViewState.Loading

                val result = withContext(Dispatchers.IO){
                    inspirationRepository.getInspirationList()
                }

                result?.let {
                    _viewState.value = HomeViewState.HomeSuccess(it)
                } ?: run {
                    _viewState.value = HomeViewState.Error
                }
            }
        }
    }
}