package com.example.quranicpath.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.quranicpath.core.data.HttpClientFactory
import com.example.quranicpath.feature_quran.data.database.DatabaseFactory
import com.example.quranicpath.feature_quran.data.database.FavoriteSurahDatabase
import com.example.quranicpath.feature_quran.data.network.KtorRemoteQuranDataSource
import com.example.quranicpath.feature_quran.data.network.RemoteQuranDataSource
import com.example.quranicpath.feature_quran.data.repository.DefaultQuranRepository
import com.example.quranicpath.feature_quran.domain.repository.QuranRepository
import com.example.quranicpath.feature_quran.presentation.SelectedSurahViewModel
import com.example.quranicpath.feature_quran.presentation.search.SearchViewModel
import com.example.quranicpath.feature_quran.presentation.surah_detail.SurahDetailViewModel
import com.example.quranicpath.feature_quran.presentation.surah_list.SurahListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        HttpClientFactory.create(get())
    }
    singleOf(::KtorRemoteQuranDataSource).bind<RemoteQuranDataSource>()
    singleOf(::DefaultQuranRepository).bind<QuranRepository>()

    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<FavoriteSurahDatabase>().favoriteSurahDao
    }

    viewModelOf(::SurahListViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::SurahDetailViewModel)
    viewModelOf(::SelectedSurahViewModel)
}