package com.a.wanandroid.screen.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a.wanandroid.component.WrapLayout
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a.wanandroid.net.bean.NavBean
import com.a.wanandroid.screen.main.base.ScreenLoading
import com.a.wanandroid.ui.theme.themeTextColor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

object S {
    var count = 0
}


@Composable
fun WebSiteScreen(viewModel: WebSiteViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsState().value
    val navies = uiState.navies


    val leftState = rememberLazyListState()
    val rightState = rememberLazyListState()
    var indicatorPosition by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    var clickFrag by remember { mutableStateOf(false) }
    Log.d("alvin", "WebSiteScreen ")


    LaunchedEffect(key1 = rightState) {
        snapshotFlow { rightState.isScrollInProgress }
            .distinctUntilChanged()
            .filter { !it }
            .collect {
                if (clickFrag) {
                    clickFrag = false
                    return@collect
                }
                val bodyPosition = rightState.firstVisibleItemIndex
                if (indicatorPosition != bodyPosition) {
                    indicatorPosition = bodyPosition
                    val visibleItemInfo =
                        leftState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == indicatorPosition }
                    val visible = visibleItemInfo != null
                    if (!visible) {
                        coroutineScope.launch {
                            leftState.animateScrollToItem(indicatorPosition)
                        }
                    }
                }
            }
    }

    val indicatorClick: ((Int) -> Unit) = { index ->
        clickFrag = true
        coroutineScope.launch {
            indicatorPosition = index
            rightState.animateScrollToItem(index = index, scrollOffset = 0)
        }
    }

    if (uiState.loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            ScreenLoading()
        }
    } else {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn(
                modifier = Modifier.width(90.dp),
                state = leftState
            ) {
                val size = navies.size
                for (i in 0 until size) {
                    item {
                        val navi = navies[i]
                        IndicatorItemContent(
                            navi,
                            i,
                            indicatorPosition,
                            indicatorClick
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 12.dp),
                state = rightState
            ) {
                val size = navies.size
                for (i in 0 until size) {
                    item {
                        val navi = navies[i]
                        BodyItemContent(navi = navi)

                    }
                }

            }
        }
    }


}


@Composable
fun IndicatorItemContent(
    navi: NavBean,
    index: Int,
    indicatorPosition: Int,
    onclick: ((Int) -> Unit)? = null
) {

    val selected = indicatorPosition == index
    val bgColor = if (selected) Color.White else Color(0xFFF7F7F9)
    val textColor = if (selected) Color.Black else Color(0xFF808394)
    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(bgColor)

            .clickable {
                onclick?.invoke(index)
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

@Composable
fun BodyItemContent(
    navi: NavBean
) {
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
                        modifier = Modifier
                            .background(Color(0xFFF7F7F9))
                            .padding(
                                horizontal = 12.dp,
                                vertical = 5.dp
                            ),
                        color = Color(0xFF808394),
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
