package com.example.quranicpath.feature_quran.data.database

import androidx.room.TypeConverter
import com.example.quranicpath.feature_quran.data.dto.AyahDto
import com.example.quranicpath.feature_quran.domain.model.Ayah
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object AyahDtoListTypeConverter {

    @TypeConverter
    fun fromAyahDtoList(list: List<Ayah>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toAyahDtoList(json: String): List<Ayah> {
        return Json.decodeFromString(json)
    }
}