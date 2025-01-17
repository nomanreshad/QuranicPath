package com.example.quranicpath.feature_quran.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurahDataDto(
    @SerialName("number") val surahNumber: Int,
    @SerialName("name") val name: String,
    @SerialName("englishName") val englishName: String,
    @SerialName("englishNameTranslation") val englishNameTranslation: String,
    @SerialName("revelationType") val revelationType: String,
    @SerialName("numberOfAyahs") val numberOfAyahs: Int,
    @SerialName("ayahs") val ayahs: List<AyahDto>
)