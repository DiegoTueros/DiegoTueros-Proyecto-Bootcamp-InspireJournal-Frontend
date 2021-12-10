package com.nttdata.inspirationjournal2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InspirationEntity (
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val created_at: String,
    val URL: String,
    val category: Category
)

@Entity
data class Category(
    @PrimaryKey val id: String,
    val name: String,
    val color_primary: String,
    val color_secondary: String,
    val icono: String
)