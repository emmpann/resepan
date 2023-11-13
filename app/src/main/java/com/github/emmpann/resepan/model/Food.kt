package com.github.emmpann.resepan.model

data class Food(
    val id: Long,
    val image: Int,
    val title: String,
    val ingredients: ArrayList<String>,
    val ways: ArrayList<String>
)
