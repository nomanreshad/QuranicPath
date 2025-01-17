package com.example.quranicpath.feature_quran.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AyahDto(
    @SerialName("number") val ayahNumberInSerially: Int,
    @SerialName("text") val text: String,
    @SerialName("numberInSurah") val ayahNumberInSurah: Int,
    @SerialName("juz") val juz: Int,
    @SerialName("manzil") val manzil: Int,
    @SerialName("page") val page: Int,
    @SerialName("ruku") val ruku: Int,
    @SerialName("hizbQuarter") val hizbQuarter: Int,
//    @SerialName("sajda") val sajda: Boolean,
)