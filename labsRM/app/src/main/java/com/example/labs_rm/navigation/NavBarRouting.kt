package com.example.labs_rm.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavBarRouting(
    val label: String,
    val icon: ImageVector
) {
    CHARACTERS(label = "Characters", icon = Icons.Filled.Person),
    LOCATIONS(label = "Locations", icon = Icons.Filled.LocationOn),
    PROFILE(label = "Profile", icon = Icons.Filled.Person)
}