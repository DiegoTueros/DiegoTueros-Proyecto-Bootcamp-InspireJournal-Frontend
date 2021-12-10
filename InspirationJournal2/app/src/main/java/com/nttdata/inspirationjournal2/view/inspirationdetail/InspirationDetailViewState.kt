package com.nttdata.inspirationjournal2.view.inspirationdetail

import com.nttdata.inspirationjournal2.model.InspirationItem

sealed class InspirationDetailViewState {
    object Loading: InspirationDetailViewState()
    data class  InspirationDetailSuccess(val inspirationDetail: InspirationItem): InspirationDetailViewState()
    object Error: InspirationDetailViewState()
}