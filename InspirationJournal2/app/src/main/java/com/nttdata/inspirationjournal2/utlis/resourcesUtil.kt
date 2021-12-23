package com.nttdata.inspirationjournal2.utlis

import com.nttdata.inspirationjournal2.R

fun imageInspiration(image: String): Int{

    when (image){
        "ic_guitar" -> return R.drawable.ic_guitar
        "ic_camera" -> return R.drawable.ic_camera
        "ic_paintbrush" -> return R.drawable.ic_paintbrush
        "ic_wing" -> return R.drawable.ic_wing
    }

    return R.drawable.ic_guitar
}

fun imageInspirationDetail(image: String): Int{

    when (image){
        "ic_guitar" -> return R.drawable.ic_only_guitar
        "ic_camera" -> return R.drawable.ic_only_camera
        "ic_paintbrush" -> return R.drawable.ic_only_paintbrush
        "ic_wing" -> return R.drawable.ic_only_wing
    }

    return R.drawable.ic_only_guitar
}

fun descriptionBackground(image: String): Int{

    when (image){
        "ic_guitar" -> return R.drawable.background_music_description
        "ic_camera" -> return R.drawable.background_photo_description
        "ic_paintbrush" -> return R.drawable.background_drawing_description
        "ic_wing" -> return R.drawable.background_poem_description
    }

    return R.drawable.background_drawing_description
}

fun backspace(image: String): Int{

    when (image){
        "ic_guitar" -> return R.drawable.ic_backspace_music
        "ic_camera" -> return R.drawable.ic_backspace_photo
        "ic_paintbrush" -> return R.drawable.ic_backspace_drawing
        "ic_wing" -> return R.drawable.ic_backspace_poem
    }

    return R.drawable.ic_backspace_music
}

fun favoriteIcon( isFavorite: Boolean): Int{
    when(isFavorite){
        true -> return R.drawable.ic_heart_purple
        false -> return R.drawable.ic_heart_grey
    }
}