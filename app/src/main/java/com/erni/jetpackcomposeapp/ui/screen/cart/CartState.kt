package com.erni.jetpackcomposeapp.ui.screen.cart

import com.erni.jetpackcomposeapp.ui.model.OrderProduct

data class CartState (
    val orderProduct: List<OrderProduct>,
    val totalRequiredPrice: Int
        )