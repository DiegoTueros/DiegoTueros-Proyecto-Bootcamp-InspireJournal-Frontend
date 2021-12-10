package com.nttdata.inspirationjournal2.view.favorites

import androidx.lifecycle.*
import com.nttdata.inspirationjournal2.data.InspirationRepository
import com.nttdata.inspirationjournal2.view.home.HomeViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private  val inspirationRepository: InspirationRepository
): ViewModel() {
    private val inspirationList: FavoritesViewModel? = null

    private val _viewState = MutableLiveData<FavoritesViewState>()
    val viewState: LiveData<FavoritesViewState> = _viewState

    fun loadFavoritesList() {
        viewModelScope.launch() {

            if(inspirationList == null){
                _viewState.value = FavoritesViewState.Loading

                val result = withContext(Dispatchers.IO){
                    inspirationRepository.getFavoriteList()
                }

                result?.let {
                    _viewState.value = FavoritesViewState.FavoriteSuccess(it)
                } ?: run {
                    _viewState.value = FavoritesViewState.Error
                }
            }
        }
    }
}