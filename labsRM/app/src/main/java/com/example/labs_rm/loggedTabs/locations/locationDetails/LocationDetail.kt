package com.example.labs_rm.loggedTabs.locations.locationDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labs_rm.databases.locationData.LocationDb
import com.example.labs_rm.loggedTabs.InfoRow
import com.example.labs_rm.viewModel.ErrorScreen
import com.example.labs_rm.viewModel.LoadingScreen
import com.example.labs_rm.viewModel.ScreenStates

@Composable
fun LocDetailWithState(
    id: Int,
    vm: LocDtViewModel = viewModel()
) {
    val state: ScreenStates by vm.state.collectAsStateWithLifecycle()

    when {
        state.isLoading -> LoadingScreen()
        state.isError -> ErrorScreen(
                errorMessage = "Error al obtener información de la ubicación.",
                switchStateClick = { vm.switchState() }
            )
        state.isLoaded -> LocationDetail(id = id)
    }
}

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


