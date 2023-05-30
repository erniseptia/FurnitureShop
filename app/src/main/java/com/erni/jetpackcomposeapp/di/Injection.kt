package com.erni.jetpackcomposeapp.di

import com.erni.jetpackcomposeapp.data.ProductRepository

object Injection {
    fun provideRepository(): ProductRepository {
        return ProductRepository.getInstance()
    }
}