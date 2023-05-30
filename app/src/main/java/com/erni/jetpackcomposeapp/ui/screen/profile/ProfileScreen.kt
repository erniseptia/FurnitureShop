package com.erni.jetpackcomposeapp.ui.screen.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erni.jetpackcomposeapp.R
import com.erni.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun MyProfile(
    @DrawableRes image: Int,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp),
            shape = CircleShape,
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .wrapContentSize()
                    .size(200.dp)
                    .clip(CircleShape)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
            Text(
                text = stringResource(R.string.detail_profile),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify,
            )
        }
    }
}

@Composable
fun ProfileScreen() {
    JetpackComposeAppTheme() {
        MyProfile(R.drawable.erni)
    }
}