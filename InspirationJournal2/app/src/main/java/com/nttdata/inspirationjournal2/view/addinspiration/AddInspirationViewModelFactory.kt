package com.nttdata.inspirationjournal2.view.addinspiration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nttdata.inspirationjournal2.data.InspirationRepository
import java.lang.IllegalArgumentException

class AddInspirationViewModelFactory (
    val inspirationRepository: InspirationRepository
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddInspirationViewModel::class.java)){
            return  AddInspirationViewModel(inspirationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}