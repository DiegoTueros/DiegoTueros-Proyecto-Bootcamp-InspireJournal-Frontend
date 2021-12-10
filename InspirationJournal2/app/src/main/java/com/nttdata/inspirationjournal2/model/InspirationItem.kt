package com.nttdata.inspirationjournal2.model

import android.os.Parcelable
import com.nttdata.inspirationjournal2.model.Category
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InspirationItem(
    val id: String?,
    val title: String?,
    val description: String?,
    val created_at: String?,
    val URL: String?,
    val status: Boolean,
    //val category: String?,
    val category: Category
): Parcelable

