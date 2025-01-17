package com.example.quranicpath.feature_quran.domain.model

data class AyahData(
    val ayahNumber: Int,
    val text: String,
    val surah: Surah,
    val hizbQuarter: Int,
    val juz: Int,
    val manzil: Int,
    val numberInSurah: Int,
    val page: Int,
    val ruku: Int,
//    val sajda: Boolean
)
