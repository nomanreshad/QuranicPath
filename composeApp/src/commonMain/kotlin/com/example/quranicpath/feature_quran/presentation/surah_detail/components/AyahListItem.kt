package com.example.quranicpath.feature_quran.presentation.surah_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quranicpath.core.presentation.DarkGreen
import com.example.quranicpath.core.presentation.LightGreen
import com.example.quranicpath.core.presentation.convertEnglishToArabicNumbers
import com.example.quranicpath.feature_quran.domain.model.Ayah
import org.jetbrains.compose.resources.Font
import quranicpath.composeapp.generated.resources.AmiriQuran_Regular
import quranicpath.composeapp.generated.resources.Poppins_Regular
import quranicpath.composeapp.generated.resources.Res

@Composable
fun AyahListItem(
    ayah: Ayah,
    modifier: Modifier = Modifier
) {
//    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
//            .clickable {
//                isExpanded = !isExpanded
//            }
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Surface(
                modifier = Modifier.size(30.dp),
                shape = CircleShape,
                border = BorderStroke(width = 2.dp, color = DarkGreen),
                color = LightGreen
            ) {
                val arabicNumber = convertEnglishToArabicNumbers(ayah.numberInSurah.toString())
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
                text = ayah.text.removeSuffix("\n").trim(),
                fontSize = 24.sp,
                lineHeight = 60.sp,
                color = Color.Black,
                fontFamily = FontFamily(Font(resource = Res.font.AmiriQuran_Regular)),
                modifier = Modifier.padding(start = 12.dp),
                textAlign = TextAlign.End
            )
        }

//        if (isExpanded) {
//            Spacer(modifier = Modifier.height(15.dp))
//            Column {
//                Text(
//                    text = if (ayah.sajda) "Sajda: Yes" else "Sajda: No",
//                    style = MaterialTheme.typography.titleSmall,
//                    color = Color.Black,
//                    fontFamily = FontFamily(Font(resource = Res.font.Poppins_Regular)),
//                    textAlign = TextAlign.Start
//                )
//            }
//        }
    }
}