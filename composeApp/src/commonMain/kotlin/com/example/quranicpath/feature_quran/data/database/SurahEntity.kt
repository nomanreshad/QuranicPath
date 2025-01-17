package com.example.quranicpath.feature_quran.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quranicpath.feature_quran.domain.model.Ayah

@Entity
data class SurahEntity(
    @PrimaryKey(autoGenerate = false) val surahNumber: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val numberOfAyahs: Int,
    val revelationType: String,
    val ayahs: List<Ayah>
)