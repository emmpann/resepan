package com.github.emmpann.resepan.model

data class Food(
    val id: Int,
    val imageUrl: String,
    val name: String,
    var isFavorite: Boolean = false,
    val ingredients: ArrayList<String>,
    val ways: ArrayList<String>
)
