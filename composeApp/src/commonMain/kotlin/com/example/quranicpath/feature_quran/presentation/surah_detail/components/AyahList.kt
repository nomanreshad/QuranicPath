package com.example.quranicpath.feature_quran.presentation.surah_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranicpath.feature_quran.domain.model.Ayah
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import quranicpath.composeapp.generated.resources.AmiriQuran_Regular
import quranicpath.composeapp.generated.resources.Res
import quranicpath.composeapp.generated.resources.bismillah

@Composable
fun AyahList(
    ayahs: List<Ayah>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = stringResource(Res.string.bismillah),
                fontSize = 26.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular)),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            HorizontalDivider(
                color = Color.Black.copy(alpha = 0.6f),
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
            )
        }

        items(
            items = ayahs,
            key = { it.numberInSurah }
        ) { ayah ->
            AyahListItem(
                ayah = ayah,
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            )
            HorizontalDivider(
                color = Color.Black.copy(alpha = 0.3f)
            )
        }
    }
}