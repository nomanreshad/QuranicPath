package com.example.quranicpath.feature_quran.presentation.surah_detail

import com.example.quranicpath.feature_quran.domain.model.Surah

sealed interface SurahDetailAction {
    data object OnBackClick : SurahDetailAction
    data object OnFavoriteClick : SurahDetailAction
    data class OnSelectedSurahChange(val surah: Surah) : SurahDetailAction
}