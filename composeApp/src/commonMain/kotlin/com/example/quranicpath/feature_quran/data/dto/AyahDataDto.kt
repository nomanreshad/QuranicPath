package com.example.quranicpath.feature_quran.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AyahDataDto(
    @SerialName("number") val ayahNumber: Int,
    @SerialName("text") val text: String,
    @SerialName("surah") val surah: SurahDto,
    @SerialName("hizbQuarter") val hizbQuarter: Int,
    @SerialName("juz") val juz: Int,
    @SerialName("manzil") val manzil: Int,
    @SerialName("numberInSurah") val numberInSurah: Int,
    @SerialName("page") val page: Int,
    @SerialName("ruku") val ruku: Int,
    @SerialName("sajda") val sajda: Boolean
)