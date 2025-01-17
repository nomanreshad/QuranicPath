package com.example.quranicpath.feature_quran.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SurahEntity::class],
    version = 1
)
@TypeConverters(AyahDtoListTypeConverter::class)
@ConstructedBy(SurahDatabaseConstructor::class)
abstract class FavoriteSurahDatabase : RoomDatabase() {
    abstract val favoriteSurahDao: FavoriteSurahDao

    companion object {
        const val DB_NAME = "surah.db"
    }
}