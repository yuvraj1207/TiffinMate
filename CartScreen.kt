package com.yuvraj.tiffinmate.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuvraj.tiffinmate.model.CartItem
import com.yuvraj.tiffinmate.ui.theme.OrangePrimary
import com.yuvraj.tiffinmate.viewmodel.CartViewModel

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier,
    onCheckoutClick: () -> Unit
){
    val cartItems = cartViewModel.cartItems
    val totalCost = cartItems.sumOf { it.price.toInt() }
    var showOptions by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Cart",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = OrangePrimary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (cartItems.isEmpty()) {
            Text("Your cart is empty.", fontSize = 16.sp)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(cartItems) { item ->
                    CartItemCard(
                        item = item,
                        onRemove = { cartViewModel.removeFromCart(item) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Total: ₹$totalCost", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(16.dp))

            if (!showOptions) {
                Button(
                    onClick = { showOptions = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
                ) {
                    Text("Checkout")
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = { /* Navigate to subscription page */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
                    ) {
                        Text("Subscribe for a Plan")
                    }

                    Button(
                        onClick = { /* Navigate to trial/select page */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
                    ) {
                        Text("Trial / Select Days")
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem, onRemove: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("₹${item.price}", fontSize = 14.sp, color = OrangePrimary)
            }

            TextButton(onClick = onRemove) {
                Text("Remove", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
