package com.a.wanandroid.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.a.wanandroid.ui.theme.contentBg

@Composable
fun MainIndexScreen() {
    Column(modifier = Modifier.fillMaxSize().background(contentBg)) {
        Text(text = "hello  main index")
    }
}