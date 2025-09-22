package com.example.labs_rm.loggedTabs.characters.charDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_rm.databases.charData.CharacterDb
import com.example.labs_rm.loggedTabs.InfoRow

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
        InfoRow(
            label = "Species:",
            value = c.species,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
        )

        InfoRow(
            label = "Status:",
            value = c.status,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
        )

        InfoRow(
            label = "Gender:",
            value = c.gender,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
        )
    }
}