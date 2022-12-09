package com.a.wanandroid.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a.wanandroid.component.refreshlayout.RefreshLazyColumn
import com.a.wanandroid.screen.main.base.ArticleItem
import com.a.wanandroid.screen.main.index.BannerViewPager
import com.a.wanandroid.screen.main.base.ScreenLoading

@Composable
fun MainIndexScreen(
    viewModel: MainIndexViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val refreshState = viewModel.refreshState.collectAsState().value
    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.loading) {
            ScreenLoading()
        } else {

            RefreshLazyColumn(state = refreshState,
                onRefresh = { viewModel.refreshData() },
                onLoadMore = { viewModel.loadMore() }) {
                item { BannerViewPager(uiState.banners) }
                uiState.topArticles.forEach { item { ArticleItem(it, true) } }
                uiState.articles.forEach { item { ArticleItem(it, false) } }
            }
        }
    }
}
