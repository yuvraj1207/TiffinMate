package com.yuvraj.tiffinmate.model

data class CartItem(
    val name: String,
    val price: String,
    val imageRes: Int,
    var quantity: Int = 1
)
