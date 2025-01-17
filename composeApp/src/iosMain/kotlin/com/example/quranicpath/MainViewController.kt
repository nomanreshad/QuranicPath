package com.example.quranicpath

import androidx.compose.ui.window.ComposeUIViewController
import com.example.quranicpath.app.App
import com.example.quranicpath.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }