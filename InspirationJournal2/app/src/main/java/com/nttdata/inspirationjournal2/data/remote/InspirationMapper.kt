package com.nttdata.inspirationjournal2.data.remote


import com.nttdata.inspirationjournal2.data.remote.response.CategoryResponse
import com.nttdata.inspirationjournal2.data.remote.response.InspirationItemResponse
import com.nttdata.inspirationjournal2.model.Category
import com.nttdata.inspirationjournal2.model.InspirationItem

fun InspirationItemResponse.toInspirationItem(): InspirationItem {
    return InspirationItem(
        id = _id,
        title = title,
        description = description,
        created_at = created_at,
        URL = URL,
        status = status,
        category = category.toCategory()
    )
}

fun CategoryResponse.toCategory(): Category {
    return Category(
        id = _id,
        name = name,
        color_primary = color_primary,
        color_secondary = color_secondary,
        icono = icono
    )
}