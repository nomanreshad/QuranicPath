package com.example.quranicpath.feature_quran.domain.repository

import com.example.quranicpath.core.domain.DataError
import com.example.quranicpath.core.domain.EmptyResult
import com.example.quranicpath.core.domain.Result
import com.example.quranicpath.feature_quran.domain.model.Ayah
import com.example.quranicpath.feature_quran.domain.model.AyahData
import com.example.quranicpath.feature_quran.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    suspend fun getSurahs(): Result<List<Surah>, DataError.Remote>
    suspend fun getSurahDetails(surahNumber: Int): Result<List<Ayah>, DataError>
    suspend fun getAyah(ayahNumber: Int): Result<AyahData, DataError.Remote>

    fun getFavoriteSurahs(): Flow<List<Surah>>
    fun isSurahFavorite(surahNumber: Int): Flow<Boolean>
    suspend fun markAsFavorite(surah: Surah): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(surahNumber: Int)
}