package com.nttdata.inspirationjournal2.view.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nttdata.inspirationjournal2.data.InspirationRepository
import java.lang.IllegalArgumentException

class FavoritesViewModelFactory (
    val inspirationRepository: InspirationRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)){
            return  FavoritesViewModel(inspirationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}