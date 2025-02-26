package com.zaed.changedinar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.components.Lazy
import com.zaed.changedinar.R
import com.zaed.changedinar.data.model.CryptoModel
import com.zaed.changedinar.ui.theme.ChangeDinarTheme

@Composable
fun CryptoItem(
    modifier: Modifier = Modifier,
    cryptoModel: CryptoModel = CryptoModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 22.5.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(cryptoModel.crypto_code.icon),
                contentDescription = null,

                modifier = Modifier
                    .size(48.dp)
                    .padding(
                        vertical = 4.5.dp,
                    )
                    .padding(end = 10.dp)
            )
            Column(
                modifier = Modifier
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = cryptoModel.crypto_code.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = cryptoModel.crypto_code.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "${cryptoModel.price_in_usd.toBigDecimal()} $",
                    modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                )
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    Text(
//                        text = "-10.5%",
//                        modifier=Modifier,
//                        fontSize = 15.sp,
//                        color = MaterialTheme.colorScheme.error,
//                        textAlign = TextAlign.End,
//                        fontWeight = FontWeight.Normal,
//                    )
//                    Icon(
//                        painter = painterResource(R.drawable.arrow_down) ,
//                        tint = MaterialTheme.colorScheme.error,
//                        contentDescription = null,
//                    )
//                }
            }

        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CreptoItemPreview() {
    ChangeDinarTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFFFFF), Color(0xFFA3D8FF)), // Adjust colors as needed
                        start = Offset(0f, 0f),
                        end = Offset(0f, 1000f) // Adjust gradient direction
                    )
                )
                .padding(top=33.dp, bottom = 12.dp)
        ) {
            items(10) {
                CryptoItem()
            }
        }
    }
}