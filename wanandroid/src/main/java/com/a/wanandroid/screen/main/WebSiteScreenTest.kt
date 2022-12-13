package com.a.wanandroid.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.a.wanandroid.component.WrapLayout
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun WebSiteScreenTest(data: MutableList<Pair<String, List<String>>> = mutableListOf()) {
    Text(text = "sdsdsds")
    val leftState = rememberLazyListState()
    val rightState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()


    Row(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(

            modifier = Modifier
                .width(90.dp)
                .background(Color.Cyan),
            state = leftState

        ) {
            val size  = data.size
            for(i in 0 until size){
                val pair = data[i]
                item {
                    Text(

                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                            .clickable {
                                coroutineScope.launch {
                                    rightState.animateScrollToItem(index = i, scrollOffset = 0)
                                }
                            },
                        textAlign = TextAlign.Center,
                        text = pair.first
                    )
                }
            }

        }
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        state = rightState) {
            data.forEach {
                item {
                    Column {
                        Text(
                            modifier = Modifier.height(30.dp),
                            textAlign = TextAlign.Center,
                            text = it.first
                        )

                        WrapLayout(
                            modifier = Modifier.padding(12.dp),
                        ) {
                            it.second.forEach { contentText ->
                                Card(
                                    modifier = Modifier.border(
                                        width = 1.dp,
                                        shape = RoundedCornerShape(5.dp),
                                        color = Color.Cyan
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 10.dp,
                                            vertical = 5.dp
                                        ), text = contentText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewWebSiteScreenTest() {
    var data: MutableList<Pair<String, List<String>>> = mutableListOf()
    for (i in 0..30) {
        var maxJ = Random.nextInt(8) + 2
        var content = mutableListOf<String>()
        for (j in 1..maxJ) {
            content.add("content ${j}")
        }
        data.add(Pair("title${i}", content))

    }
    WebSiteScreenTest(data)
}