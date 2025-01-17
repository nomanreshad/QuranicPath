package com.example.quranicpath.core.presentation

fun convertEnglishToArabicNumbers(input: String): String {
    val englishToArabicMap = mapOf(
        '0' to '٠',
        '1' to '١',
        '2' to '٢',
        '3' to '٣',
        '4' to '٤',
        '5' to '٥',
        '6' to '٦',
        '7' to '٧',
        '8' to '٨',
        '9' to '٩'
    )
    return input.map { char ->
        englishToArabicMap[char] ?: char
    }.joinToString("")
}