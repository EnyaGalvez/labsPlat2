package com.example.labs_rm.loggedTabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_rm.loggedTabs.InfoRow

@Composable
fun UserProfile(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "Icono de perfil",
            tint = MaterialTheme.colorScheme.surface,
            modifier = modifier
                .background(MaterialTheme.colorScheme.secondary, CircleShape)
                .padding(10.dp)
                .size(85.dp)
        )

        InfoRow(
            label = "Nombre:",
            value = "Enya Ayleen Gálvez Hernández",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
        )

        InfoRow(
            label = "Carné:",
            value = "24693",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
        )

        OutlinedButton(
            onClick = onLogout,
            shape = RoundedCornerShape(20.dp)
        ) {
            Text("Cerrar sesión")
        }
    }
}