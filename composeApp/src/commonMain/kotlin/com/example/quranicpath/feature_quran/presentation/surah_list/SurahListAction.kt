package com.example.quranicpath.feature_quran.presentation.surah_list

import com.example.quranicpath.feature_quran.domain.model.Surah

sealed interface SurahListAction {
//    data class OnSurahClick(val surahNumber: Int) : SurahListAction
    data class OnSurahClick(val surah: Surah) : SurahListAction
    data object OnSearchClick : SurahListAction
}