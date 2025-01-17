package com.example.quranicpath.feature_quran.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quranicpath.core.presentation.DarkGreen
import com.example.quranicpath.core.presentation.Green
import com.example.quranicpath.feature_quran.domain.model.Surah
import com.example.quranicpath.feature_quran.presentation.search.components.QuranSearchBar
import com.example.quranicpath.feature_quran.presentation.search.components.SearchAyahListItem
import com.example.quranicpath.feature_quran.presentation.search.components.SearchSurahListItem
import com.example.quranicpath.feature_quran.presentation.surah_list.components.SurahList
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import quranicpath.composeapp.generated.resources.Poppins_Regular
import quranicpath.composeapp.generated.resources.Res
import quranicpath.composeapp.generated.resources.no_search_results_for_ayah
import quranicpath.composeapp.generated.resources.no_search_results_for_surah

@Composable
fun SearchScreenRoot(
    viewModel: SearchViewModel,
    onSurahClick: (Surah) -> Unit,
    onCancelCLick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SearchScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is SearchAction.OnSurahClick -> onSurahClick(action.surah)
                SearchAction.OnCancelClick -> onCancelCLick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun SearchScreen(
    state: SearchState,
    onAction: (SearchAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val pagerState = rememberPagerState { 3 }

    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            onAction(SearchAction.OnTabSelected(pagerState.currentPage))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Green)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            QuranSearchBar(
                searchQuery = state.searchQuery,
                onSearchQueryChange = {
                    onAction(SearchAction.OnSearchQueryChange(it))
                },
                onImeSearch = {
                    keyboardController?.hide()
                },
                modifier = Modifier.widthIn(max = 400.dp).padding(10.dp)
            )
            Text(
                text = "Cancel",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular)),
                modifier = Modifier
                    .clickable {
                        onAction(SearchAction.OnCancelClick)
                    }
            )
        }

        Surface(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            color = Green
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier.widthIn(500.dp).fillMaxWidth(),
                    containerColor = Green,
                    contentColor = DarkGreen,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            color = DarkGreen,
                            modifier = Modifier.tabIndicatorOffset(tabPositions[state.selectedTabIndex])
                        )
                    }
                ) {
                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onAction(SearchAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = DarkGreen,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                        text = {
                            Text(
                                text = "Surah",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular))
                            )
                        }
                    )
                    Tab(
                        selected = state.selectedTabIndex == 1,
                        onClick = {
                            onAction(SearchAction.OnTabSelected(1))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = DarkGreen,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                        text = {
                            Text(
                                text = "Ayah",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular))
                            )
                        }
                    )
                    Tab(
                        selected = state.selectedTabIndex == 2,
                        onClick = {
                            onAction(SearchAction.OnTabSelected(2))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = DarkGreen,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                        text = {
                            Text(
                                text = "Favorites",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular))
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) { pageIndex ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when(pageIndex) {
                            0 -> {
                                if (state.isLoading) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                } else {
                                    when {
                                        state.errorMessage != null -> {
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = state.errorMessage.asString(),
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.padding(horizontal = 16.dp),
                                                    style = MaterialTheme.typography.headlineSmall,
                                                    color = MaterialTheme.colorScheme.error
                                                )
                                            }
                                        }

                                        state.surah == null -> {
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = stringResource(Res.string.no_search_results_for_surah),
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.padding(horizontal = 16.dp),
                                                    style = MaterialTheme.typography.headlineSmall,
                                                    color = MaterialTheme.colorScheme.error
                                                )
                                            }
                                        }

                                        else -> {
                                            SearchSurahListItem(
                                                surah = state.surah,
                                                modifier = Modifier
                                                    .widthIn(max = 700.dp)
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 12.dp),
                                                onSurahClick = { surah ->
                                                    onAction(SearchAction.OnSurahClick(surah))
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                            1 -> {
                                if (state.isLoading) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                } else {
                                    when {
                                        state.errorMessage != null -> {
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = state.errorMessage.asString(),
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.padding(horizontal = 16.dp),
                                                    style = MaterialTheme.typography.headlineSmall,
                                                    color = MaterialTheme.colorScheme.error
                                                )
                                            }
                                        }

                                        state.ayahData == null -> {
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = stringResource(Res.string.no_search_results_for_ayah),
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.padding(horizontal = 16.dp),
                                                    style = MaterialTheme.typography.headlineSmall,
                                                    color = MaterialTheme.colorScheme.error
                                                )
                                            }
                                        }

                                        else -> {
                                            SearchAyahListItem(
                                                ayahData = state.ayahData,
                                                modifier = Modifier
                                                    .widthIn(max = 700.dp)
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 12.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            2 -> {
                                if (state.favoriteSurahs.isEmpty()) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
//                                            text = stringResource(Res.string.no_favorite_surahs),
                                            text = "No favorite surahs",
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.padding(horizontal = 16.dp),
                                            style = MaterialTheme.typography.headlineSmall,
                                            color = MaterialTheme.colorScheme.error
                                        )
                                    }
                                } else {
                                    SurahList(
                                        surahs = state.favoriteSurahs,
                                        onSurahClick = { surah ->
                                            onAction(SearchAction.OnSurahClick(surah))
                                        },
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}