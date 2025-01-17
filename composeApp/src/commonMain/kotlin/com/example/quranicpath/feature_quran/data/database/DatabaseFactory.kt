package com.example.quranicpath.feature_quran.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<FavoriteSurahDatabase>
}