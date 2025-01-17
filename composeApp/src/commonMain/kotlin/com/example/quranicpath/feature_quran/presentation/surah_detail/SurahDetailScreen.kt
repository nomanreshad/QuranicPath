package com.example.quranicpath.feature_quran.presentation.surah_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quranicpath.core.presentation.Green
import com.example.quranicpath.core.presentation.LightGray
import com.example.quranicpath.core.presentation.SandYellow
import com.example.quranicpath.feature_quran.presentation.surah_detail.components.AyahList
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import quranicpath.composeapp.generated.resources.AmiriQuran_Regular
import quranicpath.composeapp.generated.resources.Res
import quranicpath.composeapp.generated.resources.mark_as_favorite
import quranicpath.composeapp.generated.resources.remove_from_favorites

@Composable
fun SurahDetailScreenRoot(
    viewModel: SurahDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SurahDetailScreen(
        state = state,
        onAction = { action ->
            when (action) {
                SurahDetailAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun SurahDetailScreen(
    state: SurahDetailState,
    onAction: (SurahDetailAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Green).statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.surah != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        onAction(SurahDetailAction.OnBackClick)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "(${state.surah.surahNumber}) ${state.surah.name}",
                        fontSize = 17.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular))
                    )
                    IconButton(
                        onClick = {
                            onAction(SurahDetailAction.OnFavoriteClick)
                        },
                        modifier = Modifier.padding(end = 5.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(SandYellow, Color.Transparent),
                                    radius = 70f
                                )
                            )
                    ) {
                        Icon(
                            imageVector = if (state.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            tint = Color.Red,
                            contentDescription = if (state.isFavorite) {
                                stringResource(Res.string.remove_from_favorites)
                            } else stringResource(Res.string.mark_as_favorite)
                        )
                    }
                }
            }

            Surface(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                color = LightGray,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            ) {
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
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular)),
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                        else -> {
                            AyahList(
                                ayahs = state.surah.ayahs,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}