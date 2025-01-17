package com.example.quranicpath.feature_quran.presentation

import androidx.lifecycle.ViewModel
import com.example.quranicpath.feature_quran.domain.model.Surah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedSurahViewModel: ViewModel() {

    private val _selectedSurah = MutableStateFlow<Surah?>(null)
    val selectedSurah = _selectedSurah.asStateFlow()

    fun onSelectSurah(surah: Surah?) {
        _selectedSurah.value = surah
    }
}