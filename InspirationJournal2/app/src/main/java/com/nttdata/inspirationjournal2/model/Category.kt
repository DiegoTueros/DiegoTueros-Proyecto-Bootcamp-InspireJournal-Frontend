package com.nttdata.inspirationjournal2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Category(
    val id: String,
    val name: String?,
    val color_primary: String,
    val color_secondary: String?,
    val icono: String
): Parcelable