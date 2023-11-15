package com.github.emmpann.resepan.model

data class Food(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val time: Int = 0,
    val rating: Float = 0.0f,
    var isFavorite: Boolean = false,
    val ingredients: ArrayList<String>,
    val ways: ArrayList<String>
)
