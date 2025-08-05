package com.yuvraj.tiffinmate.screens.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuvraj.tiffinmate.ui.theme.OrangePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutOptionsScreen(
    onBack: () -> Unit,
    onSelectSubscribe: () -> Unit,
    onSelectTrial: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose Checkout Option") },
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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Select your preferred checkout method", fontSize = 20.sp)

            Button(
                onClick = onSelectSubscribe,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
            ) {
                Text("Subscription Plan")
            }

            OutlinedButton(
                onClick = onSelectTrial,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Trial Plan (Select Days)")
            }
        }
    }
}
