package com.example.labs_rm.characters.charDetails

import CharacterDb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterDetail(
    id: Int,
    db: CharacterDb = CharacterDb()
) {
    val c = remember(id) {db.getCharacterById(id)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val avatarColor = MaterialTheme.colorScheme.primaryContainer

        Box(
            modifier = Modifier
                .size(160.dp)
                .background(avatarColor, CircleShape)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = c.name,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(24.dp))
        Info("Species:", c.species)
        Divider(Modifier.padding(vertical = 6.dp))
        Info("Status:", c.status)
        Divider(Modifier.padding(vertical = 6.dp))
        Info("Gender:", c.gender)
    }
}

@Composable
private fun Info(
    label: String,
    value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}