package com.yuvraj.tiffinmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yuvraj.tiffinmate.screens.*
import com.yuvraj.tiffinmate.screens.cart.CartScreen
import com.yuvraj.tiffinmate.screens.checkout.CheckoutOptionsScreen
import com.yuvraj.tiffinmate.screens.checkout.SubscribePlanScreen
import com.yuvraj.tiffinmate.screens.checkout.TrialPlanScreen
import com.yuvraj.tiffinmate.screens.edit.EditScreen
import com.yuvraj.tiffinmate.screens.home.HomeScreen
import com.yuvraj.tiffinmate.screens.kitchen.KitchenDetailsPage
import com.yuvraj.tiffinmate.ui.theme.TiffinMateTheme
import com.yuvraj.tiffinmate.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {

    enum class Screen {
        Login, Signup, MainApp, KitchenDetails,
        CheckoutOptions, SubscribePlan, TrialPlan
    }

    enum class BottomTab(val icon: ImageVector, val label: String) {
        Home(Icons.Default.Home, "Home"),
        Edit(Icons.Default.Edit, "Edit"),
        Cart(Icons.Default.ShoppingCart, "Cart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiffinMateTheme {
                var currentScreen by remember { mutableStateOf(Screen.Login) }
                var selectedTab by remember { mutableStateOf(BottomTab.Home) }

                val cartViewModel: CartViewModel = viewModel()

                when (currentScreen) {
                    Screen.Login -> LoginPage(
                        onLoginClick = { _, _ -> currentScreen = Screen.MainApp },
                        onNavigateToSignUp = { currentScreen = Screen.Signup }
                    )

                    Screen.Signup -> SignUpPage(
                        onSignUpClick = { _, _, _, _, _ -> currentScreen = Screen.MainApp },
                        onNavigateToLogin = { currentScreen = Screen.Login }
                    )

                    Screen.MainApp -> Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                selectedTab = selectedTab,
                                onTabSelected = { selectedTab = it }
                            )
                        }
                    ) { innerPadding ->
                        when (selectedTab) {
                            BottomTab.Home -> HomeScreen(
                                onKitchenClick = { currentScreen = Screen.KitchenDetails },
                                modifier = Modifier.padding(innerPadding)
                            )

                            BottomTab.Edit -> EditScreen(modifier = Modifier.padding(innerPadding))

                            BottomTab.Cart -> CartScreen(
                                cartViewModel = cartViewModel,
                                modifier = Modifier.padding(innerPadding),
                                onCheckoutClick = { currentScreen = Screen.CheckoutOptions }
                            )
                        }
                    }

                    Screen.KitchenDetails -> KitchenDetailsPage(
                        cartViewModel = cartViewModel,
                        onBack = { currentScreen = Screen.MainApp },
                        onGoToCart = {
                            currentScreen = Screen.MainApp
                            selectedTab = BottomTab.Cart
                        }
                    )

                    Screen.CheckoutOptions -> CheckoutOptionsScreen(
                        onBack = { currentScreen = Screen.MainApp },
                        onSelectSubscribe = { currentScreen = Screen.SubscribePlan },
                        onSelectTrial = { currentScreen = Screen.TrialPlan }
                    )

                    Screen.SubscribePlan -> SubscribePlanScreen(
                        onBack = { currentScreen = Screen.CheckoutOptions },
                        onConfirm = { days, price ->
                            // You can save plan to ViewModel here
                            currentScreen = Screen.MainApp
                            selectedTab = BottomTab.Home
                        }
                    )

                    Screen.TrialPlan -> TrialPlanScreen(
                        onBack = { currentScreen = Screen.CheckoutOptions },
                        onConfirm = { selectedDays ->
                            // Save selected days to ViewModel if needed
                            currentScreen = Screen.MainApp
                            selectedTab = BottomTab.Home
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedTab: MainActivity.BottomTab,
    onTabSelected: (MainActivity.BottomTab) -> Unit
) {
    NavigationBar {
        MainActivity.BottomTab.values().forEach { tab ->
            NavigationBarItem(
                icon = { Icon(tab.icon, contentDescription = tab.label) },
                label = { Text(tab.label) },
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) }
            )
        }
    }
}
