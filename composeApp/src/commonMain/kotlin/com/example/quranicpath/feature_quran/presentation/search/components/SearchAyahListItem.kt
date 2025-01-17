package com.example.quranicpath.feature_quran.presentation.search.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranicpath.core.presentation.DarkGreen
import com.example.quranicpath.core.presentation.LightGreen
import com.example.quranicpath.core.presentation.SurahItemBG
import com.example.quranicpath.core.presentation.convertEnglishToArabicNumbers
import com.example.quranicpath.feature_quran.domain.model.AyahData
import org.jetbrains.compose.resources.Font
import quranicpath.composeapp.generated.resources.AmiriQuran_Regular
import quranicpath.composeapp.generated.resources.Poppins_Regular
import quranicpath.composeapp.generated.resources.Res

@Composable
fun SearchAyahListItem(
    ayahData: AyahData,
    modifier: Modifier = Modifier
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = modifier
            .then(
                if (isExpanded) Modifier.wrapContentHeight() else Modifier.height(100.dp)
            )
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(20.dp),
                clip = false
            )
            .clip(RoundedCornerShape(20.dp))
            .background(SurahItemBG)
            .clickable(onClick = {
                isExpanded = !isExpanded
            })
            .padding(start = 10.dp, end = 10.dp, bottom = 8.dp)
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "${ayahData.surah.surahNumber}. ${ayahData.surah.englishName} (${ayahData.surah.englishNameTranslation})",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular)),
                overflow = TextOverflow.Ellipsis
            )
            Spacer(
                modifier = Modifier.height(5.dp)
            )
            Text(
                text = "${ayahData.surah.numberOfAyahs} Ayahs - ${ayahData.surah.revelationType}",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular)),
                overflow = TextOverflow.Ellipsis
            )

            if (isExpanded) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = Color.Gray, thickness = 1.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Surface(
                        modifier = Modifier.size(30.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = 2.dp, color = DarkGreen),
                        color = LightGreen
                    ) {
                        val arabicNumber = convertEnglishToArabicNumbers(ayahData.numberInSurah.toString())
                        Text(
                            text = arabicNumber,
                            fontSize = if (arabicNumber.length == 3) 10.sp else 12.sp,
                            color = DarkGreen,
                            fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular)),
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = ayahData.text.removeSuffix("\n").trim(),
                        fontSize = 24.sp,
                        lineHeight = 60.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular)),
                        modifier = Modifier.padding(start = 10.dp),
                        textAlign = TextAlign.End
                    )
                }
                Spacer(modifier = Modifier.padding(top = 30.dp))
            }
        }

        Text(
            text = ayahData.surah.name,
            modifier = Modifier.align(Alignment.BottomEnd),
            fontSize = 17.sp,
            color = Color.Black,
            fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular)),
            overflow = TextOverflow.Ellipsis
        )
    }
}