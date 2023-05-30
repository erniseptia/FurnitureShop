package com.erni.jetpackcomposeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erni.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import com.erni.jetpackcomposeapp.ui.theme.Shapes
import com.erni.jetpackcomposeapp.R

@Composable
fun CartItem(
    productId: Long,
    image: Int,
    title: String,
    totalHarga: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.harga,
                    totalHarga
                ),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2,
            )
        }
        ProductCounter(
            orderId = productId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(productId, count + 1) },
            onProductDecreased = { onProductCountChanged(productId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
   JetpackComposeAppTheme() {
        CartItem(
            4, R.drawable.mb1, "Meja Belajar/Meja Komputer Cool Desk", 500000, 0,
            onProductCountChanged = { productId, count -> },
        )
    }
}