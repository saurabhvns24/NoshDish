package com.example.dish

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@SuppressLint("DefaultLocale")
@Composable
fun ScheduleCookingDialog(onDismissRequest: () -> Unit) {
    var hour by remember { mutableIntStateOf(6) }
    var minute by remember { mutableIntStateOf(30) }
    var isAm by remember { mutableStateOf(true) }

    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Schedule cooking time",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF001970)
                    )
                    Image(
                        modifier = Modifier.clickable { onDismissRequest() },
                        painter = painterResource(R.drawable.close_button),
                        contentDescription = "close"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Time picker with hour and minute selection
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .background(Color(0xFFF3F4F6), RoundedCornerShape(8.dp))
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Hour picker
                        LazyColumn(
                            modifier = Modifier.size(50.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(12) { item ->
                                Text(
                                    text = String.format("%02d", item + 1),
                                    fontSize = 24.sp,
                                    color = if (hour == item + 1) Color(0xFF001970) else Color.Gray,
                                    modifier = Modifier.clickable { hour = item + 1 }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(":", fontSize = 24.sp, color = Color.Gray)

                        Spacer(modifier = Modifier.width(8.dp))

                        // Minute picker
                        LazyColumn(
                            modifier = Modifier.size(50.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(60) { item ->
                                Text(
                                    text = String.format("%02d", item),
                                    fontSize = 24.sp,
                                    color = if (minute == item) Color(0xFF001970) else Color.Gray,
                                    modifier = Modifier.clickable { minute = item }
                                )
                            }
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "AM",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isAm) Color(0xFF001970) else Color.Gray,
                            modifier = Modifier
                                .clickable { isAm = true }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .background(
                                    if (isAm) Color(0xFFE3F2FD) else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "PM",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (!isAm) Color(0xFF001970) else Color.Gray,
                            modifier = Modifier
                                .clickable { isAm = false }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .background(
                                    if (!isAm) Color(0xFFE3F2FD) else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = { /* Handle delete action */ }) {
                        Text(text = "Delete", color = Color.Red)
                    }
                    TextButton(onClick = { /* Handle reschedule action */ }) {
                        Text(text = "Re-schedule", color = Color.Gray)
                    }
                    Button(
                        onClick = { /* Handle cook now action */ },
                        colors = ButtonDefaults.buttonColors(Color(0xFFFF9800))
                    ) {
                        Text(text = "Cook Now", color = Color.White)
                    }
                }
            }
        }
    }
}