package com.yuvraj.tiffinmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.yuvraj.tiffinmate.model.CartItem

class CartViewModel : ViewModel() {

    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addItem(item: CartItem) {
        val existingItem = _cartItems.find { it.name == item.name }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            _cartItems.add(item)
        }
    }

    fun removeItem(item: CartItem) {
        _cartItems.remove(item)
    }

    fun removeFromCart(item: CartItem) {
        _cartItems.remove(item)
    }

    fun clearCart() {
        _cartItems.clear()
    }
}
