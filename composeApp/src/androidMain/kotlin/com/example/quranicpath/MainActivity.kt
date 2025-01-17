package com.example.quranicpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quranicpath.app.App
import com.example.quranicpath.feature_quran.domain.model.AyahData
import com.example.quranicpath.feature_quran.domain.model.Surah
import com.example.quranicpath.feature_quran.presentation.search.SearchScreen
import com.example.quranicpath.feature_quran.presentation.search.SearchState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppAndroidPreview() {
//    SurahListItem(
//        surah = Surah(
//            surahNumber = 1,
//            name = "سُورَةُ ٱلْفَاتِحَةِ",
//            englishName = "The Opener",
//            englishNameTranslation = "Al Fatiha",
//            numberOfAyahs = 7,
//            revelationType = "Meccan"
//        ),
//        modifier = Modifier.fillMaxWidth(),
//        onClick = {}
//    )

//    SurahListScreen(
//        state = SurahListState(
//            surahs = listOf(
//                Surah(
//                    surahNumber = 1,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 2,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 3,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 4,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 5,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 6,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 7,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 8,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 9,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//                Surah(
//                    surahNumber = 10,
//                    name = "سُورَةُ ٱلْفَاتِحَةِ",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    numberOfAyahs = 7,
//                    revelationType = "Meccan"
//                ),
//            ),
//            isLoading = false,
//            errorMessage = null
//        ),
//        onAction = {}
//    )

//    SurahDetailScreen(
//        state = SurahDetailState(
//            isLoading = false,
//            surahData = SurahData(
//                surahNumber = 1,
//                name = "سُورَةُ ٱلْفَاتِحَةِ",
//                englishName = "The Opener",
//                englishNameTranslation = "Al Fatiha",
//                revelationType = "Meccan",
//                numberOfAyahs = 7,
//                ayahs = listOf(
//                    Ayah(
//                        ayahNumber = 6222,
//        text = "بِسۡمِ ٱللَّهِ ٱلرَّحۡمَـٰنِ ٱلرَّحِیمِ قُلۡ هُوَ ٱللَّهُ أَحَد بِسۡمِ ٱللَّهِ ٱلرَّحۡمَـٰنِ ٱلرَّحِیمِ قُلۡ هُوَ ٱللَّهُ أَحَدٌٌ\n",
//                        numberInSurah = 1,
//                        juz = 30,
//                        manzil = 7,
//                        page = 604,
//                        ruku = 554,
//                        hizbQuarter = 240,
//                        sajda = false
//                    ),
//                    Ayah(
//                        ayahNumber = 6223,
//                        text = "ٱللَّهُ ٱلصَّمَدٌُ\n",
//                        numberInSurah = 2,
//                        juz = 30,
//                        manzil = 7,
//                        page = 604,
//                        ruku = 554,
//                        hizbQuarter = 240,
//                        sajda = false
//                    ),
//                    Ayah(
//                        ayahNumber = 6224,
//                        text = "لَمۡ یَلِدۡ وَلَمۡ یُولَدٌۡ\n",
//                        numberInSurah = 3,
//                        juz = 30,
//                        manzil = 7,
//                        page = 604,
//                        ruku = 554,
//                        hizbQuarter = 240,
//                        sajda = false
//                    ),
//                    Ayah(
//                        ayahNumber = 6225,
//                        text = "وَلَمۡ یَكُن لَّهُۥ كُفُوًا أَحَدُۢ\n",
//                        numberInSurah = 4,
//                        juz = 30,
//                        manzil = 7,
//                        page = 604,
//                        ruku = 554,
//                        hizbQuarter = 240,
//                        sajda = false
//                    )
//                )
//            )
//        ),
//        onAction = {}
//    )

//    SearchScreen(
//        state = SearchState(
//            searchQuery = "1",
//            isLoading = false,
//            errorMessage = null,
//            ayahData = AyahData(
//                ayahNumber = 1,
//                text = "ٱللَّهُ ٱلصَّمَدٱللَّهُ ٱلصَّمَدٱللَّهُ ٱلصَّمَدٱللَّهُ ٱلصَّمَدٱللَّهُ ٱلصَّمَدٱللَّهُ ٱلصَّمَدٌُ\n",
//                numberInSurah = 1,
//                juz = 30,
//                manzil = 7,
//                page = 604,
//                ruku = 554,
//                hizbQuarter = 240,
//                sajda = false,
//                surah = Surah(
//                    surahNumber = 114,
//                    name = "سُورَةُ ٱلْفَاتِحَة",
//                    englishName = "The Opener",
//                    englishNameTranslation = "Al Fatiha",
//                    revelationType = "Meccan",
//                    numberOfAyahs = 7,
//                )
//            ),
//            selectedTabIndex = 1
//        ),
//        onAction = {}
//    )

//    SearchAyahListItem(
//        ayahData = AyahData(
//            ayahNumber = 6223,
//            text = "ٱللَّهُ ٱلصَّمَدٌُ\n",
//            numberInSurah = 2,
//            juz = 30,
//            manzil = 7,
//            page = 604,
//            ruku = 554,
//            hizbQuarter = 240,
//            sajda = false,
//            surah = Surah(
//                surahNumber = 114,
//                name = "سُورَةُ ٱلْفَاتِحَة",
//                englishName = "The Opener",
//                englishNameTranslation = "Al Fatiha",
//                revelationType = "Meccan",
//                numberOfAyahs = 7,
//            )
//        ),
//        onAyahClick = {}
//    )
}