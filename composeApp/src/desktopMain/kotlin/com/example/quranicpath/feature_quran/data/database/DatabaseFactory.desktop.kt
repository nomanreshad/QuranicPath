package com.example.quranicpath.feature_quran.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavoriteSurahDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "QuranicPath")
            os.contains("mac") -> File(userHome, "Library/Application Support/QuranicPath")
            else -> File(userHome, ".local/share/QuranicPath")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, FavoriteSurahDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}