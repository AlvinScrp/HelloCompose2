package com.a.wanandroid.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a.wanandroid.R
import com.a.wanandroid.ui.theme.setStatusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SplashScreen() {
    setStatusBarColor()
    Box(
        modifier = Modifier
            .background((Color.White))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.mipmap.ic_world),
            contentDescription = "splash logo"
        )
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}