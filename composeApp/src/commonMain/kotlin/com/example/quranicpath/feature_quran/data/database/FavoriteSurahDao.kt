package com.example.quranicpath.feature_quran.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteSurahDao {

    @Upsert
    suspend fun upsert(surah: SurahEntity)

    @Query("SELECT * FROM SurahEntity")
    fun getFavoriteSurahs(): Flow<List<SurahEntity>>

    @Query("SELECT * FROM SurahEntity WHERE surahNumber = :surahNumber")
    suspend fun getFavoriteSurah(surahNumber: Int): SurahEntity?

    @Query("DELETE FROM SurahEntity WHERE surahNumber = :surahNumber")
    suspend fun deleteFavoriteSurah(surahNumber: Int)
}