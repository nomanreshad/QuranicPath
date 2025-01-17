package com.example.quranicpath.feature_quran.data.network

import com.example.quranicpath.core.domain.DataError
import com.example.quranicpath.core.domain.Result
import com.example.quranicpath.feature_quran.data.dto.AyahSearchResponseDto
import com.example.quranicpath.feature_quran.data.dto.SurahDetailsResponseDto
import com.example.quranicpath.feature_quran.data.dto.SurahsResponseDto

interface RemoteQuranDataSource {
    suspend fun getSurahs(): Result<SurahsResponseDto, DataError.Remote>
    suspend fun getSurahDetails(surahNumber: Int): Result<SurahDetailsResponseDto, DataError.Remote>
    suspend fun getAyah(ayahNumber: Int): Result<AyahSearchResponseDto, DataError.Remote>
}