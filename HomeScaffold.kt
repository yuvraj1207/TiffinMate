package com.yuvraj.tiffinmate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.yuvraj.tiffinmate.MainActivity.BottomTab
import com.yuvraj.tiffinmate.screens.cart.CartScreen
import com.yuvraj.tiffinmate.screens.edit.EditScreen
import com.yuvraj.tiffinmate.screens.home.HomeScreen

@Composable
fun HomeScaffold(
    currentTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentTab = currentTab,
                onTabSelected = onTabSelected
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentTab) {
                BottomTab.Home -> HomeScreen(onKitchenClick = {}, modifier = Modifier)
                BottomTab.Edit -> EditScreen(modifier = Modifier)
                BottomTab.Cart -> CartScreen(modifier = Modifier)
            }
        }
    }
}

private fun BoxScope.CartScreen(modifier: Modifier.Companion) {}

@Composable
fun BottomNavigationBar(
    currentTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit
) {
    NavigationBar {
        BottomTab.values().forEach { tab ->
            NavigationBarItem(
                icon = { Icon(tab.icon, contentDescription = tab.label) },
                label = { Text(tab.label) },
                selected = currentTab == tab,
                onClick = { onTabSelected(tab) }
            )
        }
    }
}
