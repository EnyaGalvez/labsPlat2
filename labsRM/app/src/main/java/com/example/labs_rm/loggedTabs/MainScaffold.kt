package com.example.labs_rm.loggedTabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.labs_rm.loggedTabs.characters.charList.CharacterListRoute
import com.example.labs_rm.loggedTabs.locations.locationList.LocationListRoute
import com.example.labs_rm.loggedTabs.profile.UserProfileRoute
import com.example.labs_rm.navigation.NavBarRouting

val BottomBarNavigation: Saver<NavBarRouting, String> = Saver(
    save = { it.name },
    restore = { NavBarRouting.valueOf(it) }
)

fun NavBarRouting.navRoute(): Any = when (this) {
    NavBarRouting.CHARACTERS -> CharacterListRoute
    NavBarRouting.LOCATIONS -> LocationListRoute
    NavBarRouting.PROFILE -> UserProfileRoute

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    title: String,
    showBack: Boolean,
    currentRoute: NavBarRouting,
    onTabSelected: (NavBarRouting) -> Unit,
    onBack: (() -> Unit)? = null,
    content: @Composable () -> Unit

) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (showBack) {
                        IconButton(onClick = { onBack?.invoke() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "regresar")
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },

        bottomBar = {
            val items = NavBarRouting.values()
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = item == currentRoute,
                        onClick = { onTabSelected(item) },
                        icon = { Icon( imageVector = item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) {
        inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 16.dp),
        ) {
            content()
        }
    }

}
