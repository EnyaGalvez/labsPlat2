package com.example.labs_rm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.labs_rm.loggedTabs.MainScaffold
import com.example.labs_rm.loggedTabs.characters.charList.CharacterListRoute
import com.example.labs_rm.loggedTabs.characters.charList.CharacterList
import com.example.labs_rm.loggedTabs.characters.charDetails.CharacterDetailRoute
import com.example.labs_rm.loggedTabs.characters.charDetails.CharacterDetail
import com.example.labs_rm.loggedTabs.locations.locationDetails.LocationDetail
import com.example.labs_rm.loggedTabs.locations.locationDetails.LocationDetailRoute
import com.example.labs_rm.loggedTabs.locations.locationList.LocationListRoute
import com.example.labs_rm.loggedTabs.locations.locationList.LocationList
import com.example.labs_rm.loggedTabs.navRoute
import com.example.labs_rm.loggedTabs.profile.UserProfile
import com.example.labs_rm.loggedTabs.profile.UserProfileRoute
import com.example.labs_rm.login.LoginRoute
import com.example.labs_rm.login.Login

@Composable
fun AppNavHost() {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            Login(
                onStart = {
                    nav.navigate(CharacterListRoute) {
                        popUpTo<LoginRoute> { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<CharacterListRoute> {
            MainScaffold(
                title = "Characters",
                showBack = false,
                currentRoute = NavBarRouting.CHARACTERS,
                onTabSelected = { item ->
                    nav.navigate(item.navRoute()) {
                        popUpTo<CharacterListRoute> { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                CharacterList(
                    onCharacterClick = { id ->
                        nav.navigate(CharacterDetailRoute(id))
                    }
                )
            }
        }

        composable<CharacterDetailRoute> { backStackEntry ->
            val argsChar = backStackEntry.toRoute<CharacterDetailRoute>()
            MainScaffold(
                title = "Character Detail",
                showBack = true,
                currentRoute = NavBarRouting.CHARACTERS,
                onTabSelected = { item ->
                    nav.navigate(item.navRoute()) {
                        popUpTo<CharacterListRoute> { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onBack = { nav.popBackStack() }
            ) {
                CharacterDetail(id = argsChar.id)
            }
        }

        composable<LocationListRoute> {
            MainScaffold(
                title = "Locations",
                showBack = false,
                currentRoute = NavBarRouting.LOCATIONS,
                onTabSelected = { item ->
                    nav.navigate(item.navRoute()) {
                        popUpTo<LocationListRoute> { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                LocationList(
                    onLocationClick = { id ->
                        nav.navigate(LocationDetailRoute(id))
                    }
                )
            }
        }

        composable<LocationDetailRoute> { backStackEntry ->
            val argsLocation = backStackEntry.toRoute<LocationDetailRoute>()
            MainScaffold(
                title = "Character Detail",
                showBack = true,
                currentRoute = NavBarRouting.LOCATIONS,
                onTabSelected = { item ->
                    nav.navigate(item.navRoute()) {
                        popUpTo<LocationListRoute> { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onBack = { nav.popBackStack() }
            ) {
                LocationDetail(id = argsLocation.id)
            }
        }

        composable<UserProfileRoute> {
            MainScaffold(
                title = "Profile",
                showBack = false,
                currentRoute = NavBarRouting.PROFILE,
                onTabSelected = { item ->
                    nav.navigate(item.navRoute()) {
                        popUpTo<UserProfileRoute> { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                UserProfile(
                    onLogout = {
                        nav.navigate(LoginRoute) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}