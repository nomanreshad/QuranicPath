package com.example.quranicpath.feature_quran.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object SurahDatabaseConstructor: RoomDatabaseConstructor<FavoriteSurahDatabase> {
    override fun initialize(): FavoriteSurahDatabase
}