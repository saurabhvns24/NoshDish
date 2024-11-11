@file:JvmName("MainActivityKt")

package com.example.dish
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun MainScreen(dishes: List<Dish>) {
    var searchText by remember { mutableStateOf("") }
    var selectedDish by remember { mutableStateOf<Dish?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    // New state to track the selected sidebar item
    var selectedItem by remember { mutableStateOf("Cook") }

    Row {
        Sidebar(
            selectedItem = selectedItem,
            onItemClick = { item ->
                selectedItem = item // Update the selected item when an item is clicked
            }
        )
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(searchText = searchText, onSearchTextChanged = { searchText = it })
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "What's on your mind?",
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            CategoryChipsRow()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Recommendations",
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Recommendations(dishes = dishes.filter {
                it.dishName.contains(
                    searchText,
                    ignoreCase = true
                )
            }) {
                selectedDish = it
                showDialog = true
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                CustomButton(
                    text = "Explore all dishes",
                    backgroundColor = Color(0xFFFF9800),
                    onClick = { /* Handle explore all dishes action */ }
                )
                CustomButton(
                    text = "Confused what to cook?",
                    backgroundColor = Color(0xFFFF9800),
                    onClick = { /* Handle confused action */ }
                )
            }
        }
    }
    if (showDialog) {
        ScheduleCookingDialog(onDismissRequest = { showDialog = false })
    }
}

@Composable
fun Sidebar(
    selectedItem: String, // Pass the selected item
    onItemClick: (String) -> Unit // Callback to update the selected item
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(150.dp)
            .background(Color(0xFFECEFF1)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(32.dp)) // Top padding

        SidebarItem(
            iconId = R.drawable.ic_cook,
            label = "Cook",
            isSelected = selectedItem == "Cook",
            onClick = { onItemClick("Cook") }
        )

        SidebarItem(
            iconId = R.drawable.ic_favorites,
            label = "Favorites",
            isSelected = selectedItem == "Favorites",
            onClick = { onItemClick("Favorites") }
        )

        SidebarItem(
            iconId = R.drawable.ic_manual,
            label = "Manual",
            isSelected = selectedItem == "Manual",
            onClick = { onItemClick("Manual") }
        )

        SidebarItem(
            iconId = R.drawable.ic_device,
            label = "Device",
            isSelected = selectedItem == "Device",
            onClick = { onItemClick("Device") }
        )

        SidebarItem(
            iconId = R.drawable.ic_preferences,
            label = "Preferences",
            isSelected = selectedItem == "Preferences",
            onClick = { onItemClick("Preferences") }
        )

        SidebarItem(
            iconId = R.drawable.ic_settings,
            label = "Settings",
            isSelected = selectedItem == "Settings",
            onClick = { onItemClick("Settings") }
        )
    }
}

@Composable
fun SidebarItem(
    iconId: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}
@Composable
fun CustomButton(
    text: String,
    backgroundColor: Color,
    textColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(56.dp) // Adjust height as per your design
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}
@Composable
fun SearchBar(searchText: String, onSearchTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        placeholder = {
            Text(
                text = "Search for dish or ingredient",
                fontWeight = FontWeight.Normal
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search), // replace with actual search icon drawable
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp) // Adjust height as per design
            .padding(horizontal = 16.dp)
            .background(Color(0xFFECEFF1), CircleShape), // Background color and shape
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFECEFF1), // Adjust color to match the screenshot
            unfocusedContainerColor = Color(0xFFECEFF1),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Gray
        ),
        shape = CircleShape, // Rounded corners for the search bar
        singleLine = true
    )
}
@Composable
fun CategoryChipsRow() {
    // State to track the currently selected chip label
    var selectedChip by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CategoryChip(
            iconId = R.drawable.rice_icon,
            label = "Rice items",
            isSelected = selectedChip == "Rice items",
            onClick = { selectedChip = "Rice items" }
        )
        CategoryChip(
            iconId = R.drawable.indian_food,
            label = "Indian",
            isSelected = selectedChip == "Indian",
            onClick = { selectedChip = "Indian" }
        )
        CategoryChip(
            iconId = R.drawable.curries,
            label = "Curries",
            isSelected = selectedChip == "Curries",
            onClick = { selectedChip = "Curries" }
        )
        CategoryChip(
            iconId = R.drawable.soups,
            label = "Soups",
            isSelected = selectedChip == "Soups",
            onClick = { selectedChip = "Soups" }
        )
        CategoryChip(
            iconId = R.drawable.dessert,
            label = "Desserts",
            isSelected = selectedChip == "Desserts",
            onClick = { selectedChip = "Desserts" }
        )
    }
}

@Composable
fun CategoryChip(
    iconId: Int,
    label: String,
    isSelected: Boolean, // New parameter to indicate if the chip is selected
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else Color(0xFFECEFF1),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
fun Recommendations(dishes: List<Dish>, onDishSelected: (Dish) -> Unit) {
    RecommendationSection(dishes = dishes, onDishSelected)
}
@Composable
fun RecommendationSection(dishes: List<Dish>, onDishSelected: (Dish) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dishes) { dish ->
            RecommendationCard(
                dish,
                onDishSelected = onDishSelected,
            )
        }
    }
}
@Composable
fun RecommendationCard(
    dish: Dish,
    onDishSelected: (Dish) -> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp) // Adjust card width as needed
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = { onDishSelected(dish) } // Action when card is clicked
    ) {
        Column {
            // Dish image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                AsyncImage(
                    model = dish.imageUrl,
                    contentDescription = dish.dishName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Rating badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .background(
                            color = Color(0xFFFFD700), // Background color for rating badge
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${dish.rating}",
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Dish title
            Text(
                text = dish.dishName,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Cooking time and preparation type
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_time), // Replace with your clock icon resource
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
                Text(
                    text = "$0 • $10",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}