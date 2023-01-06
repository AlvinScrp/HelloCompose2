package com.example.compiletest.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ReplaceableGroupTest(condition: Boolean) {
    if (condition) {
        Text("Hello") //Text Node 1
    } else {
        Text("World") //Text Node 2
    }
}

@Preview
@Composable
fun PreviewReplaceableGroupTest(){
    ReplaceableGroupTest(condition = true)
}

