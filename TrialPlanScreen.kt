package com.yuvraj.tiffinmate.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack // ✅ Required import
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuvraj.tiffinmate.ui.theme.OrangePrimary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrialPlanScreen(
    onConfirm: (List<String>) -> Unit,
    onBack: () -> Unit
) {
    val availableDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val selectedDays = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Days for Trial Plan") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Select the days you want to receive tiffin service:",
                fontSize = 18.sp,
                color = Color.Black
            )

            availableDays.forEach { day ->
                val isSelected = selectedDays.contains(day)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = if (isSelected) OrangePrimary.copy(alpha = 0.2f) else Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            if (isSelected) selectedDays.remove(day) else selectedDays.add(day)
                        }
                        .padding(vertical = 14.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = day,
                        fontSize = 16.sp,
                        color = if (isSelected) OrangePrimary else Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onConfirm(selectedDays) },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedDays.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
            ) {
                Text(text = "Confirm Days & Continue")
            }
        }
    }
}
