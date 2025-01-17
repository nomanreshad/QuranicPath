package com.example.quranicpath.feature_quran.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranicpath.core.presentation.SurahItemBG
import com.example.quranicpath.feature_quran.domain.model.Surah
import org.jetbrains.compose.resources.Font
import quranicpath.composeapp.generated.resources.AmiriQuran_Regular
import quranicpath.composeapp.generated.resources.Poppins_Regular
import quranicpath.composeapp.generated.resources.Res

@Composable
fun SearchSurahListItem(
    surah: Surah,
    onSurahClick: (Surah) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(100.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(20.dp),
                clip = false
            )
            .clip(RoundedCornerShape(20.dp))
            .background(SurahItemBG)
            .clickable(onClick = {
                onSurahClick(surah)
            })
            .padding(start = 10.dp, end = 10.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${surah.surahNumber}. ${surah.englishName} (${surah.englishNameTranslation})",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular)),
                overflow = TextOverflow.Ellipsis
            )
            Spacer(
                modifier = Modifier.height(5.dp)
            )
            Text(
                text = "${surah.numberOfAyahs} Ayahs - ${surah.revelationType}",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular)),
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = surah.name,
            modifier = Modifier.align(Alignment.BottomEnd),
            fontSize = 17.sp,
            color = Color.Black,
            fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular)),
            overflow = TextOverflow.Ellipsis
        )
    }
}