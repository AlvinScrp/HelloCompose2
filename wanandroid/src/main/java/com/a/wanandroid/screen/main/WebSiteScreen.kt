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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.a.wanandroid.component.WrapLayout
import kotlinx.coroutines.launch
import kotlin.random.Random
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a.wanandroid.component.refreshlayout.RefreshLazyColumn
import com.a.wanandroid.screen.main.base.ArticleItem
import com.a.wanandroid.screen.main.base.ScreenLoading
import com.a.wanandroid.screen.main.index.BannerViewPager
import com.a.wanandroid.ui.theme.themeTextColor

@Composable
fun WebSiteScreen(viewModel: WebSiteViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsState().value
    val navies = uiState.navies
    val leftState = rememberLazyListState()
    val rightState = rememberLazyListState()
    val selectPosition by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()


    if (uiState.loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            ScreenLoading()
        }
    } else {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn(

                modifier = Modifier
                    .width(90.dp),
                state = leftState

            ) {
                val size = navies.size
                for (i in 0 until size) {
                    val navi = navies[i]
                    val selected = i == selectPosition
                    val bgColor = if (selected) Color.White else Color(0xFFF7F7F9)
                    val textColor = if (selected) Color.Black else Color(0xFF808394)
                    item {
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                                .background(bgColor)
                                .clickable {
                                    coroutineScope.launch {
                                        rightState.animateScrollToItem(index = i, scrollOffset = 0)
                                    }
                                }, contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = navi.name.orEmpty(),
                                fontSize = 13.sp,
                                color = textColor,
                            )
                            if (selected) {
                                Spacer(
                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .size(3.dp, 12.dp)
                                        .background(themeTextColor)
                                )
                            }
                        }

                    }
                }

            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(12.dp),
                state = rightState
            ) {
                val size = navies.size
                for (i in 0 until size) {
                    val navi = navies[i]
                    item {
                        Column {
                            Text(
                                modifier = Modifier.padding(top = 10.dp),
                                text = navi.name.orEmpty(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )

                            WrapLayout(
                                modifier = Modifier.padding(8.dp),
                            ) {
                                navi.articles?.forEach { article ->
                                    Box(
                                        modifier = Modifier
                                            .padding(5.dp)

                                            .clip(RoundedCornerShape(4.dp))


                                    ) {
                                        Text(
                                            modifier = Modifier .background(Color(0xFFF7F7F9))
                                                .padding(
                                                horizontal = 10.dp,
                                                vertical = 5.dp
                                            ),
                                            color = Color(0xFF4D4D4D),
                                            fontSize = 14.sp,
                                            text = article.title.orEmpty(),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
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


}
