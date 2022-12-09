package com.a.wanandroid.screen.main.index

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.a.wanandroid.net.bean.Banner
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay


@Composable
@OptIn(ExperimentalPagerApi::class)
fun BannerViewPager(banners: List<Banner>) {
    val pageSize = banners.size

//    val startIndex = 2147483647
    val start = 1000000000
    val pagerState = rememberPagerState(initialPage = start + 1)

    val screenWidth = LocalConfiguration.current.screenWidthDp
    Log.d("alvin", "screenWidth:${screenWidth}")
    val imageWidth = 270
    val imageHeight = 150
    val horizontalPadding = (screenWidth - imageWidth) / 2
    if (banners.isNotEmpty()) {
        Column() {


            HorizontalPager(
                count = Int.MAX_VALUE,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 60.dp),
            ) { cur ->
                val i = realPage(cur, start, pageSize)
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .width(imageWidth.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .height(imageHeight.dp),
                        model = banners[i].imagePath,
                        contentDescription = banners[i].title
                    )
                }
            }
//            selectedContentColor = Color(0xFF1e80ff),
//            unselectedContentColor = Color(0xFF616161),
//            val map: (Int) -> Int = { cur-> realPage(cur,start,pageSize)   }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color(0x221e80ff),
                inactiveColor = Color(0x22616161),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 12.dp),
                pageCount = pageSize,
                pageIndexMapping = { cur -> realPage(cur, start, pageSize) }
            )
            LaunchedEffect(key1 = "autoScroll") {
                while (true) {
                    delay(2000L)
                    val curPage = pagerState.currentPage
                    val nextPage = curPage + 1
                    pagerState.animateScrollToPage(nextPage)
                }
            }
        }
    }
}


fun realPage(cur: Int, start: Int, pageSize: Int): Int {
    var index = (cur - start) % pageSize
    if (index < 0) {
        index += pageSize
    }
    return index
}