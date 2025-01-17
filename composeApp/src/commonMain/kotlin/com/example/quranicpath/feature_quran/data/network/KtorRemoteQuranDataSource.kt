package com.example.quranicpath.feature_quran.data.network

import com.example.quranicpath.core.data.safeCall
import com.example.quranicpath.core.domain.DataError
import com.example.quranicpath.core.domain.Result
import com.example.quranicpath.feature_quran.data.dto.AyahSearchResponseDto
import com.example.quranicpath.feature_quran.data.dto.SurahDetailsResponseDto
import com.example.quranicpath.feature_quran.data.dto.SurahsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val QURAN_BASE_URL = "https://api.alquran.cloud/v1"

class KtorRemoteQuranDataSource(
    private val httpClient: HttpClient
): RemoteQuranDataSource {

    override suspend fun getSurahs(): Result<SurahsResponseDto, DataError.Remote> {
        return safeCall<SurahsResponseDto> {
            httpClient.get(
                urlString = "$QURAN_BASE_URL/surah"
            )
        }
    }

    override suspend fun getSurahDetails(surahNumber: Int): Result<SurahDetailsResponseDto, DataError.Remote> {
        return safeCall<SurahDetailsResponseDto> {
            httpClient.get(
                urlString = "$QURAN_BASE_URL/surah/$surahNumber"
            )
        }
    }

    override suspend fun getAyah(ayahNumber: Int): Result<AyahSearchResponseDto, DataError.Remote> {
        return safeCall<AyahSearchResponseDto> {
            httpClient.get(
                urlString = "$QURAN_BASE_URL/ayah/$ayahNumber"
            )
        }
    }
}