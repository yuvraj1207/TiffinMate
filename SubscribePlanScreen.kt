package com.yuvraj.tiffinmate.screens.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuvraj.tiffinmate.ui.theme.OrangePrimary

@Composable
fun SubscribePlanScreen(
    onBack: () -> Unit,
    onConfirm: (Int, Int) -> Unit // duration in days, total price
) {
    val plans = listOf(
        "1 Week (7 days)" to 350,
        "1 Month (30 days)" to 1200,
        "3 Months (90 days)" to 3200
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Choose Your Subscription Plan", fontSize = 22.sp, color = OrangePrimary)

        plans.forEachIndexed { index, (label, price) ->
            Card(
                onClick = { selectedIndex = index },
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedIndex == index) OrangePrimary.copy(0.1f) else MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = label, fontSize = 16.sp)
                    Text(text = "₹$price", fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f)
            ) {
                Text("Back")
            }

            Button(
                onClick = {
                    val (label, price) = plans[selectedIndex]
                    val days = when (selectedIndex) {
                        0 -> 7
                        1 -> 30
                        2 -> 90
                        else -> 0
                    }
                    onConfirm(days, price)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
            ) {
                Text("Confirm")
            }
        }
    }
}
