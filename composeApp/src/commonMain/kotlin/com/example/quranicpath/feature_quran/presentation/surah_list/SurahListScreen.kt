package com.example.quranicpath.feature_quran.presentation.surah_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quranicpath.core.presentation.Green
import com.example.quranicpath.core.presentation.DarkGreen
import com.example.quranicpath.feature_quran.domain.model.Surah
import com.example.quranicpath.feature_quran.presentation.surah_list.components.SurahList
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import quranicpath.composeapp.generated.resources.AmiriQuran_Regular
import quranicpath.composeapp.generated.resources.Res
import quranicpath.composeapp.generated.resources.bismillah

@Composable
fun SurahListScreenRoot(
    viewModel: SurahListViewModel = koinViewModel(),
    onSurahClick: (Surah) -> Unit,
    onSearchClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SurahListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is SurahListAction.OnSurahClick -> onSurahClick(action.surah)
                SurahListAction.OnSearchClick -> onSearchClick()
            }
        }
    )
}

@Composable
fun SurahListScreen(
    state: SurahListState,
    onAction: (SurahListAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Green).statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.bismillah),
            fontSize = 24.sp,
            color = Color.Black,
            fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular))
        )
        IconButton(
            onClick = {
                onAction(SurahListAction.OnSearchClick)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Black
            )
        }
        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Surface(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            color = DarkGreen,
            shape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        when {
                            state.errorMessage != null -> {
                                Text(
                                    text = state.errorMessage.asString(),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                            else -> {
                                SurahList(
                                    surahs = state.surahs,
                                    onSurahClick = { surahNumber ->
                                        onAction(SurahListAction.OnSurahClick(surahNumber))
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