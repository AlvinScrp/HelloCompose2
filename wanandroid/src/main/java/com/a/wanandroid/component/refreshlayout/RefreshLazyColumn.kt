package com.a.wanandroid.component.refreshlayout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zj.refreshlayout.SwipeRefreshLayout


@Composable
fun RefreshLazyColumn(
    state: RefreshLazyColumnState,
    onRefresh: () -> Unit ={},
    onLoadMore: () -> Unit = {},
    content: LazyListScope.() -> Unit
) {
    SwipeRefreshLayout(isRefreshing = state.isRefreshing, onRefresh = onRefresh) {
        LazyColumn {
            content()
            if(state.enableLoadMore) {
                item {
                    LaunchedEffect(key1 = "foot") {
                        Log.d("alvin", "RefreshLazyColumnFooter LaunchedEffect")
                        onLoadMore()
                    }
                    RefreshLazyColumnFooter(state)
                }
            }
        }
    }
}

@Composable
fun RefreshLazyColumnFooter(state: RefreshLazyColumnState) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        val text = when {
            state.isLoadingMore -> "加载中"
            !state.hasMore -> "到底了"
            else -> "上拉加载更多数据"
        }
        Text(text = text)
    }
}

data class RefreshLazyColumnState(
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMore: Boolean = true,
    val enableRefresh: Boolean = true,
    val enableLoadMore: Boolean = true
)