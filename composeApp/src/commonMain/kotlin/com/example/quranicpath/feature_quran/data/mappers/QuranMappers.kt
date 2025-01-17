package com.example.quranicpath.feature_quran.data.mappers

import com.example.quranicpath.feature_quran.data.database.SurahEntity
import com.example.quranicpath.feature_quran.data.dto.AyahDataDto
import com.example.quranicpath.feature_quran.data.dto.AyahDto
import com.example.quranicpath.feature_quran.data.dto.SurahDataDto
import com.example.quranicpath.feature_quran.data.dto.SurahDto
import com.example.quranicpath.feature_quran.domain.model.Ayah
import com.example.quranicpath.feature_quran.domain.model.AyahData
import com.example.quranicpath.feature_quran.domain.model.Surah
import com.example.quranicpath.feature_quran.domain.model.SurahData

fun SurahDto.toSurah(): Surah {
    return Surah(
        surahNumber = surahNumber,
        name = name,
        englishName = englishName,
        englishNameTranslation = englishNameTranslation,
        numberOfAyahs = numberOfAyahs,
        revelationType = revelationType,
        ayahs = emptyList()
    )
}

fun SurahDataDto.toSurahData(): SurahData {
    return SurahData(
//        surahNumber = surahNumber,
//        name = name,
//        englishName = englishName,
//        englishNameTranslation = englishNameTranslation,
//        revelationType = revelationType,
//        numberOfAyahs = numberOfAyahs,
        ayahs = ayahs.map { it.toAyah() }
    )
}

fun AyahDto.toAyah(): Ayah {
    return Ayah(
        ayahNumber = ayahNumberInSerially,
        text = text,
        numberInSurah = ayahNumberInSurah,
        juz = juz,
        manzil = manzil,
        page = page,
        ruku = ruku,
        hizbQuarter = hizbQuarter,
//        sajda = sajda
    )
}

fun AyahDataDto.toAyahData(): AyahData {
    return AyahData(
        ayahNumber = ayahNumber,
        text = text,
        surah = surah.toSurah(),
        hizbQuarter = hizbQuarter,
        juz = juz,
        manzil = manzil,
        numberInSurah = numberInSurah,
        page = page,
        ruku = ruku,
//        sajda = sajda
    )
}

fun Surah.toSurahEntity(): SurahEntity {
    return SurahEntity(
        surahNumber = surahNumber,
        name = name,
        englishName = englishName,
        englishNameTranslation = englishNameTranslation,
        numberOfAyahs = numberOfAyahs,
        revelationType = revelationType,
        ayahs = ayahs
    )
}

fun SurahEntity.toSurah(): Surah {
    return Surah(
        surahNumber = surahNumber,
        name = name,
        englishName = englishName,
        englishNameTranslation = englishNameTranslation,
        numberOfAyahs = numberOfAyahs,
        revelationType = revelationType,
        ayahs = ayahs
    )
}