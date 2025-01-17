package com.example.quranicpath.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.quranicpath.feature_quran.presentation.SelectedSurahViewModel
import com.example.quranicpath.feature_quran.presentation.search.SearchScreenRoot
import com.example.quranicpath.feature_quran.presentation.search.SearchViewModel
import com.example.quranicpath.feature_quran.presentation.surah_detail.SurahDetailAction
import com.example.quranicpath.feature_quran.presentation.surah_detail.SurahDetailScreenRoot
import com.example.quranicpath.feature_quran.presentation.surah_detail.SurahDetailViewModel
import com.example.quranicpath.feature_quran.presentation.surah_list.SurahListScreenRoot
import com.example.quranicpath.feature_quran.presentation.surah_list.SurahListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.QuranGraph
        ) {
            navigation<Route.QuranGraph>(
                startDestination = Route.SurahList
            ) {
                composable<Route.SurahList>(
//                    exitTransition = { slideOutHorizontally() },
//                    popEnterTransition = { slideInHorizontally() }
                ) {
                    val viewModel = koinViewModel<SurahListViewModel>()
                    val selectedSurahViewModel =
                        it.sharedKoinViewModel<SelectedSurahViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedSurahViewModel.onSelectSurah(null)
                    }

                    SurahListScreenRoot(
                        viewModel = viewModel,
                        onSurahClick = { surah ->
                            selectedSurahViewModel.onSelectSurah(surah)
                            navController.navigate(Route.SurahDetail(surah.surahNumber))
                        },
                        onSearchClick = {
                            navController.navigate(Route.Search)
                        }
                    )
                }

                composable<Route.Search> {
                    val viewModel = koinViewModel<SearchViewModel>()
                    val selectedSurahViewModel =
                        it.sharedKoinViewModel<SelectedSurahViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedSurahViewModel.onSelectSurah(null)
                    }

                    SearchScreenRoot(
                        viewModel = viewModel,
                        onSurahClick = { surah ->
                            selectedSurahViewModel.onSelectSurah(surah)
                            navController.navigate(Route.SurahDetail(surah.surahNumber))
                        },
                        onCancelCLick = {
                            navController.navigateUp()
                        }
                    )
                }

                composable<Route.SurahDetail>(
//                    enterTransition = { slideInHorizontally { initialOffset ->
//                        initialOffset
//                    } },
//                    exitTransition = { slideOutHorizontally { initialOffset ->
//                        initialOffset
//                    } }
                ) {
                    val viewModel = koinViewModel<SurahDetailViewModel>()
                    val selectedSurahViewModel =
                        it.sharedKoinViewModel<SelectedSurahViewModel>(navController)
                    val selectedSurah by selectedSurahViewModel.selectedSurah.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedSurah) {
                        selectedSurah?.let { surah ->
                            viewModel.onAction(SurahDetailAction.OnSelectedSurahChange(surah))
                        }
                    }

                    SurahDetailScreenRoot(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}