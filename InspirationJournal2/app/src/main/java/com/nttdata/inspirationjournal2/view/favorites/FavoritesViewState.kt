package com.nttdata.inspirationjournal2.view.favorites

import com.nttdata.inspirationjournal2.model.InspirationItem

sealed class FavoritesViewState {
    object Loading: FavoritesViewState()
    data class  FavoriteSuccess(val inspirationItem: List<InspirationItem>): FavoritesViewState()
    object Error: FavoritesViewState()
}