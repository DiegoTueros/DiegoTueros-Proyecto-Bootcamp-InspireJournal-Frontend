package com.nttdata.inspirationjournal2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String?
): Parcelable