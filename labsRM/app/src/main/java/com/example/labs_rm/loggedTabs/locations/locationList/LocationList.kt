package com.example.labs_rm.loggedTabs.locations.locationList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labs_rm.databases.locationData.Location
import com.example.labs_rm.databases.locationData.LocationDb
import com.example.labs_rm.viewModel.ErrorScreen
import com.example.labs_rm.viewModel.LoadingScreen

@Composable
fun LocListWithState(
    onLocationClick: (Int) -> Unit,
    vm: LocListViewModel = viewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()

    when {
        state.isLoading -> LoadingScreen()
        state.isError -> ErrorScreen(
                errorMessage = "Error al obtener lista de ubicaciones.",
                switchStateClick = { vm.switchState() }
            )
        state.isLoaded -> LocationList(onLocationClick = onLocationClick)
    }
}

@Composable
fun LocationList(
    onLocationClick: (Int) -> Unit,
    db: LocationDb = LocationDb()
) {
    val loctions = remember { db.getAllLocations() }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues( vertical = 8.dp )
    ) {
        items(items = loctions, key = { it.id }) { l ->
            LocationInsight(
                location = l,
                onClick = { onLocationClick(l.id) }
            )
        }
    }

}

@Composable
fun LocationInsight(
    location: Location,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = location.type,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}