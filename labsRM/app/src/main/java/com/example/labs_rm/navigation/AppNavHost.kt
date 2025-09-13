package com.example.labs_rm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.labs_rm.characters.MainScaffold
import com.example.labs_rm.characters.charList.CharacterListRoute
import com.example.labs_rm.characters.charList.CharacterList
import com.example.labs_rm.characters.charDetails.CharacterDetailRoute
import com.example.labs_rm.characters.charDetails.CharacterDetail
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
                showBack = false
            ) {
                CharacterList(
                    onCharacterClick = { id ->
                        nav.navigate(CharacterDetailRoute(id))
                    }
                )
            }
        }

        composable<CharacterDetailRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<CharacterDetailRoute>()
            MainScaffold(
                title = "Character Detail",
                showBack = true,
                onBack = { nav.popBackStack() }
            ) {
                CharacterDetail(id = args.id)
            }
        }
    }
}