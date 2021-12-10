package com.nttdata.inspirationjournal2.view.home

import com.nttdata.inspirationjournal2.model.InspirationItem

sealed class HomeViewState {
    object Loading: HomeViewState()
    data class  HomeSuccess(val inspirationItem: List<InspirationItem>): HomeViewState()
    object Error: HomeViewState()
}
