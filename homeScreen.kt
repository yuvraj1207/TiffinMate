package com.yuvraj.tiffinmate.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.myapplication.components.RecommendedComboCard
import com.yuvraj.tiffinmate.MainActivity
import com.yuvraj.tiffinmate.R

@Composable
fun HomeScreen(
    onKitchenClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
            .padding(horizontal = 18.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color(0xFF27214D))
            Icon(Icons.Default.AccountCircle, contentDescription = "Account", tint = Color(0xFF27214D))
        }

        // Greeting
        Text(
            text = "Hello, Welcome to TiffinMate!",
            fontSize = 20.sp,
            color = Color(0xFFFF2714),
            modifier = Modifier.padding(vertical = 24.dp)
        )

        // Search Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F1F1), RoundedCornerShape(17.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
            Spacer(Modifier.width(12.dp))
            Text("Search for Kitchen...", fontSize = 16.sp, color = Color.Gray)
        }

        // Recommended Kitchens
        Text(
            text = "Recommended Kitchens",
            fontSize = 22.sp,
            color = Color(0xFF3F5B26),
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(17.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(
                listOf(
                    Triple("Copper Leaf", "1000", R.drawable.food),
                    Triple("Sabor", "800", R.drawable.food)
                )
            ) { (name, price, image) ->
                RecommendedComboCard(
                    name = name,
                    price = price,
                    imageRes = image,
                    onHeartClick = onKitchenClick
                )
            }
        }

        // Categories
        Text(
            text = "Categories",
            fontSize = 22.sp,
            color = Color(0xFF3F5B26),
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            items(listOf("Popular", "MostSelling", "Hottest")) {
                Text(text = it, fontSize = 16.sp, color = Color(0xFF3F5B26))
            }
        }

        // Vertical Popular Combos
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            RecommendedComboCard("Popular Item", "1200", R.drawable.food, onHeartClick = onKitchenClick)
            RecommendedComboCard("Most Selling", "950", R.drawable.eat, onHeartClick = onKitchenClick)
            RecommendedComboCard("Hottest Deal", "1100", R.drawable.logo, onHeartClick = onKitchenClick)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
