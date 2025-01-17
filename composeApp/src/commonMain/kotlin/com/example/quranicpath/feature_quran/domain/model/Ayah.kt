package com.example.quranicpath.feature_quran.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Ayah(
    val ayahNumber: Int,
    val text: String,
    val numberInSurah: Int,
    val juz: Int,
    val manzil: Int,
    val page: Int,
    val ruku: Int,
    val hizbQuarter: Int,
//    val sajda: Boolean,
)