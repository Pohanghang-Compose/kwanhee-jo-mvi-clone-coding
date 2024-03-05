package com.koreatech.mviclonecode

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.koreatech.mviclonecode.ui.components.pages.details.DetailsPage
import com.koreatech.mviclonecode.ui.components.pages.details.DetailsSideEffect
import com.koreatech.mviclonecode.ui.components.pages.details.DetailsViewModel
import com.koreatech.mviclonecode.ui.components.pages.init.InitPage
import com.koreatech.mviclonecode.ui.components.pages.init.InitSideEffect
import com.koreatech.mviclonecode.ui.components.pages.init.InitViewModel
import com.koreatech.mviclonecode.ui.components.pages.library.LibraryPage
import com.koreatech.mviclonecode.ui.components.pages.library.LibrarySideEffect
import com.koreatech.mviclonecode.ui.components.pages.library.LibraryViewModel
import com.koreatech.mviclonecode.ui.screen.Screen
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.orbitmvi.orbit.compose.collectState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Init.route) {
                    addInit(navController = navController)
                    addLibrary(navController = navController)
                    addDetails(navController = navController)
                }
            }
        }
    }

    private fun NavGraphBuilder.addInit(navController: NavController) {
        composable(route = Screen.Init.route) {
            val viewModel = getComposeViewModel<InitViewModel>()
            val state by viewModel.collectAsState()
            viewModel.collectSideEffect {
                Log.e("aaa", "side effect status : ${it}")
                when (it) {
                    is InitSideEffect.Completed -> {
                        navController.navigate(route = Screen.Library.route)
                    }
                }
            }

            InitPage(state = state, onRetry = { viewModel.retry() })
        }
    }

    private fun NavGraphBuilder.addLibrary(navController: NavController) {
        composable(route = Screen.Library.route) {
            val viewModel = getComposeViewModel<LibraryViewModel>()
            val state by viewModel.collectAsState()
            Log.e("aaa", "details state : ${state.status}")

            viewModel.collectSideEffect {
                when (it) {
                    is LibrarySideEffect.ShowDetail -> {
                        navController.navigate(route = Screen.Details.createRoute(it.id))
                    }
                }
            }

            LibraryPage(
                state = state,
                onShowDetail = { id -> viewModel.showDetails(id) },
                onSearchPokemon = { text -> viewModel.searchPokemon(text) }
            )
        }
    }

    private fun NavGraphBuilder.addDetails(navController: NavController) {
        composable(route = Screen.Details.route) {
            val viewModel = getComposeViewModel<DetailsViewModel>(parameters = {
                parametersOf(
                    Screen.Details.getArgumentId(it)
                )
            })
            val state by viewModel.collectAsState()
            viewModel.collectSideEffect {
                when (it) {
                    is DetailsSideEffect.Back -> {
                        navController.popBackStack()
                    }
                }
            }

            DetailsPage(state = state, onBack = { navController.popBackStack() })
        }
    }
}
