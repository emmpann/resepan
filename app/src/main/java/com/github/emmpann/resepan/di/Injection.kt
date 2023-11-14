package com.github.emmpann.resepan.di

import android.content.Context
import com.github.emmpann.resepan.data.FoodRepository

object Injection {
    fun provideRepository(context: Context): FoodRepository = FoodRepository.getInstance(context)
}