package com.example.labs_rm.loggedTabs.locations.locationDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_rm.databases.locationData.LocationDb
import com.example.labs_rm.loggedTabs.InfoRow

@Composable
fun LocationDetail(
    id: Int,
    db: LocationDb = LocationDb()
) {
    val l = remember(id) {db.getLocationById(id)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = l.name,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(24.dp))

        InfoRow(
            label = "ID:",
            value = l.id.toString(),
            color = MaterialTheme.colorScheme.onSurface
        )

        InfoRow(
            label = "Type:",
            value = l.type,
            color = MaterialTheme.colorScheme.onSurface
        )

        InfoRow(
            label = "Dimensions:",
            value = l.dimension,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


