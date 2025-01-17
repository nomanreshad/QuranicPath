package com.example.quranicpath.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object QuranGraph : Route

    @Serializable
    data object SurahList : Route

    @Serializable
    data class SurahDetail(val surahNumber: Int) : Route

    @Serializable
    data object Search : Route
}