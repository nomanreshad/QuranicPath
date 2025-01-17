package com.example.quranicpath.feature_quran.data.repository

import androidx.sqlite.SQLiteException
import com.example.quranicpath.core.domain.DataError
import com.example.quranicpath.core.domain.EmptyResult
import com.example.quranicpath.core.domain.Result
import com.example.quranicpath.core.domain.map
import com.example.quranicpath.feature_quran.data.database.FavoriteSurahDao
import com.example.quranicpath.feature_quran.data.mappers.toAyah
import com.example.quranicpath.feature_quran.data.mappers.toAyahData
import com.example.quranicpath.feature_quran.data.mappers.toSurah
import com.example.quranicpath.feature_quran.data.mappers.toSurahData
import com.example.quranicpath.feature_quran.data.mappers.toSurahEntity
import com.example.quranicpath.feature_quran.data.network.RemoteQuranDataSource
import com.example.quranicpath.feature_quran.domain.model.Ayah
import com.example.quranicpath.feature_quran.domain.model.AyahData
import com.example.quranicpath.feature_quran.domain.model.Surah
import com.example.quranicpath.feature_quran.domain.model.SurahData
import com.example.quranicpath.feature_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultQuranRepository(
    private val remoteQuranDataSource: RemoteQuranDataSource,
    private val favoriteSurahDao: FavoriteSurahDao
): QuranRepository {

    override suspend fun getSurahs(): Result<List<Surah>, DataError.Remote> {
        return remoteQuranDataSource
            .getSurahs()
            .map { dto ->
                dto.results.map { it.toSurah() }
            }
    }

    override suspend fun getSurahDetails(surahNumber: Int): Result<List<Ayah>, DataError> {
        val localResult = favoriteSurahDao.getFavoriteSurah(surahNumber)
        return if (localResult == null) {
            remoteQuranDataSource
                .getSurahDetails(surahNumber)
                .map { dto ->
                    dto.data.ayahs.map { it.toAyah() }
                }
        } else {
            Result.Success(localResult.ayahs)
        }
    }

    override suspend fun getAyah(ayahNumber: Int): Result<AyahData, DataError.Remote> {
        return remoteQuranDataSource
            .getAyah(ayahNumber)
            .map { dto ->
                dto.data.toAyahData()
            }
    }

    override fun getFavoriteSurahs(): Flow<List<Surah>> {
        return favoriteSurahDao
            .getFavoriteSurahs()
            .map { surahEntities ->
                surahEntities.map { it.toSurah() }
            }
    }

    override fun isSurahFavorite(surahNumber: Int): Flow<Boolean> {
        return favoriteSurahDao
            .getFavoriteSurahs()
            .map { bookEntities ->
                bookEntities.any { it.surahNumber == surahNumber }
            }
    }

    override suspend fun markAsFavorite(surah: Surah): EmptyResult<DataError.Local> {
        return try {
            favoriteSurahDao.upsert(surah.toSurahEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(surahNumber: Int) {
        favoriteSurahDao.deleteFavoriteSurah(surahNumber)
    }
}