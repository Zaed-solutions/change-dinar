package com.zaed.changedinar.ui.converter.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaed.changedinar.data.model.Currency
import com.zaed.changedinar.data.model.CurrencyIcon
import com.zaed.changedinar.ui.theme.ChangeDinarTheme
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertibleCurrencyItem(
    modifier: Modifier = Modifier,
    currency: Currency,
    price: Double,
    focusRequester: FocusRequester = remember { FocusRequester() },
    isConvertible: Boolean,
    onClick: () -> Unit = {},
    onValueChanged: (Double) -> Unit = {}
) {
    var textValue by remember { mutableStateOf(price.toString()) }
    Surface (
        modifier = modifier,
        onClick = onClick,
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(20.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(currency.icon.iconRes),
                contentDescription = "Currency Icon",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
            ) {
                Text(
                    text = currency.code,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        lineHeight = 24.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = currency.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp,
                        lineHeight = 15.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Surface (
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(1.dp, if(isConvertible) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent),
            ){
                BasicTextField(
                    value = when{
                        price == 0.0 -> ""
                        isConvertible -> textValue
                        else -> NumberFormat.getInstance().format(price)
                    },
                    onValueChange = { newText ->
                        val formattedText = newText.replace(',', '.') // Allow comma as decimal
                        if (formattedText.isEmpty()) {
                            textValue = ""
                            onValueChanged(0.0)
                        } else if (formattedText.matches(Regex("^\\d*\\.?\\d*\$"))) {
                            textValue = formattedText
                            onValueChanged(formattedText.toDoubleOrNull() ?: 0.0)
                        }
                    },
                    readOnly = !isConvertible,
                    enabled = isConvertible,
                    singleLine = true,
//                shape = MaterialTheme.shapes.small,
//                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = MaterialTheme.colorScheme.surface,
//                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
//                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
//                    unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
//                ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.width(84.dp).focusRequester(focusRequester),
                    decorationBox = {
                        OutlinedTextFieldDefaults.DecorationBox(
                            value = if (price == 0.0) "" else textValue,
                            enabled = isConvertible,
                            innerTextField = it,
                            interactionSource = remember { MutableInteractionSource() },
                            visualTransformation = VisualTransformation.None,
                            singleLine = true,
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                focusedBorderColor = Color.Transparent,
                                disabledBorderColor =  Color.Transparent,
                                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                disabledTextColor =  MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                        )
                    }
                )
            }
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = currency.symbol,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 15.sp,
                    lineHeight = 15.sp
                ),
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_9_pro")
@Composable
private fun Preview() {
    ChangeDinarTheme {
        ConvertibleCurrencyItem(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
            currency = Currency(
                icon = CurrencyIcon.USD,
                name = "United States Dollar",
                code = "USD",
                symbol = "$"
            ),
            price = 0.0,
            isConvertible = true,
            onClick = {},
            onValueChanged = {}
        )
    }
}