package com.example.quranicpath.feature_quran.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurahDto(
    @SerialName("number") val surahNumber: Int,
    @SerialName("name") val name: String,
    @SerialName("englishName") val englishName: String,
    @SerialName("englishNameTranslation") val englishNameTranslation: String,
    @SerialName("numberOfAyahs") val numberOfAyahs: Int,
    @SerialName("revelationType") val revelationType: String
)