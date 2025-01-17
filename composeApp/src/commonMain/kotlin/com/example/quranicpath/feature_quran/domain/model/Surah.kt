package com.example.quranicpath.feature_quran.domain.model

data class Surah(
    val surahNumber: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val numberOfAyahs: Int,
    val revelationType: String,
    val ayahs: List<Ayah>
)