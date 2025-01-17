package com.example.quranicpath.feature_quran.presentation.surah_detail

import com.example.quranicpath.core.presentation.UiText
import com.example.quranicpath.feature_quran.domain.model.Surah
import com.example.quranicpath.feature_quran.domain.model.SurahData

data class SurahDetailState(
    val isLoading: Boolean = true,
    val surah: Surah? = null,
    val isFavorite: Boolean = false,
    val errorMessage: UiText? = null
)