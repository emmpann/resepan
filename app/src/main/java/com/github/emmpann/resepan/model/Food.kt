package com.github.emmpann.resepan.model

data class Food(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val ingredients: ArrayList<String>,
    val ways: ArrayList<String>
)
