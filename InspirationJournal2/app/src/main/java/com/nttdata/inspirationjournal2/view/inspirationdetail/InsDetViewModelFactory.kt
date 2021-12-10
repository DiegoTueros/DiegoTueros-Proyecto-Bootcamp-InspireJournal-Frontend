package com.nttdata.inspirationjournal2.view.inspirationdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nttdata.inspirationjournal2.data.InspirationRepository
import java.lang.IllegalArgumentException


class InsDetViewModelFactory(
    val inspirationRepository: InspirationRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InspirationDetailViewModel::class.java)){
            return  InspirationDetailViewModel(inspirationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}