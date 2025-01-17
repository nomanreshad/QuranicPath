package com.example.quranicpath.feature_quran.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurahDetailsResponseDto(
    @SerialName("data") val data: SurahDataDto,
)