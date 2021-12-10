package com.nttdata.inspirationjournal2.model

import android.os.Message
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Auth(
    val token: String?,
    val user: User?,
    val message: String?,
    val status: Boolean?
): Parcelable
