package com.example.quranicpath.feature_quran.presentation.surah_list

import com.example.quranicpath.core.presentation.UiText
import com.example.quranicpath.feature_quran.domain.model.Surah

data class SurahListState(
    val surahs: List<Surah> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)