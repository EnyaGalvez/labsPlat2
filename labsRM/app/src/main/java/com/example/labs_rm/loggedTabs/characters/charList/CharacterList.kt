package com.example.labs_rm.loggedTabs.characters.charList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labs_rm.databases.charData.CharacterDb
import com.example.labs_rm.databases.charData.Character
import com.example.labs_rm.viewModel.CommonStateViewModel
import com.example.labs_rm.viewModel.ErrorScreen
import com.example.labs_rm.viewModel.LoadingScreen
import com.example.labs_rm.viewModel.ScreenStates

@Composable
fun CharListWithState(
    onCharacterClick: (Int) -> Unit,
    vm: ChrListViewModel = viewModel()
) {
    val state: ScreenStates by vm.state.collectAsStateWithLifecycle()

    when {
        state.isLoading -> LoadingScreen()
        state.isError -> ErrorScreen(
                errorMessage = "Error al obtener la lista de personajes.",
                switchStateClick = { vm.switchState() }
            )
        state.isLoaded -> CharacterList(onCharacterClick = onCharacterClick)
    }
}

@Composable
fun CharacterList(
    onCharacterClick: (Int) -> Unit,
    db: CharacterDb = CharacterDb()
) {
    val chars = remember { db.getAllCharacters() }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues( vertical = 8.dp )
    ) {
        items(items = chars, key = { it.id }) { c ->
            CharInsight(
                character = c,
                onClick = { onCharacterClick(c.id) }
            )
        }
    }

}

@Composable
fun CharInsight(
    character: Character,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape)
        )

        Spacer(Modifier.width(16.dp))

        Column(Modifier.weight(1f)) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${character.species} - ${character.status}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

