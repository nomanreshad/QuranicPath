package com.example.quranicpath

import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.quranicpath.app.App
import com.example.quranicpath.di.initKoin
import org.jetbrains.skia.Image
import java.io.File
import javax.swing.ImageIcon

fun main() {
    initKoin()
    application {
        val osName = System.getProperty("os.name").lowercase()
        val appIconPath = when {
            osName.contains("windows") -> "src/desktopMain/desktopResources/windows/app_icon.ico"
            osName.contains("mac") -> "src/desktopMain/desktopResources/mac/app_icon.icns"
            osName.contains("linux") -> "src/desktopMain/desktopResources/linux/app_icon.png"
            else -> "src/desktopMain/desktopResources/default/app_icon.png"
        }
        val appIcon = loadIconAsPainter(appIconPath)

        Window(
            onCloseRequest = ::exitApplication,
            title = "Quranic Path",
            icon = appIcon
        ) {
            App()
        }
    }
}

private fun loadIconAsPainter(iconPath: String): BitmapPainter {
    val image = Image.makeFromEncoded(File(iconPath).readBytes())
    return BitmapPainter(image.toComposeImageBitmap())
}