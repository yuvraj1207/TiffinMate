package com.yuvraj.tiffinmate.screens.kitchen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuvraj.tiffinmate.R
import com.yuvraj.tiffinmate.model.CartItem
import com.yuvraj.tiffinmate.ui.theme.OrangePrimary
import com.yuvraj.tiffinmate.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitchenDetailsPage(
    kitchenName: String = "Copper Leaf",
    kitchenDescription: String = "Authentic homemade meals with rich flavors.",
    onBack: () -> Unit,
    onGoToCart: () -> Unit,
    cartViewModel: CartViewModel
) {
    val foodItems = listOf(
        Triple("Veg Combo", "100", R.drawable.food),
        Triple("Non-Veg Combo", "120", R.drawable.logo),
        Triple("Tiffin Special", "90", R.drawable.eat)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = kitchenName, color = OrangePrimary) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = OrangePrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFF7F0))
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.eat),
                contentDescription = "Kitchen Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = kitchenDescription,
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Available Combos",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(foodItems) { (itemName, itemPrice, imageRes) ->
                    FoodItemCard(
                        name = itemName,
                        price = itemPrice,
                        imageRes = imageRes,
                        onAddToCart = {
                            cartViewModel.addItem(
                                CartItem(
                                    name = itemName,
                                    price = itemPrice,
                                    imageRes = imageRes
                                )
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {  onGoToCart() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
            ) {
                Text("Go to Cart")
            }
        }
    }
}

@Composable
fun FoodItemCard(
    name: String,
    price: String,
    imageRes: Int,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "₹$price", color = OrangePrimary)
            }

            Button(onClick = onAddToCart) {
                Text("Add")
            }
        }
    }
}
