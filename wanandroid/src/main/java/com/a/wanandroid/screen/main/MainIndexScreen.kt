package com.a.wanandroid.screen.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.a.wanandroid.component.refreshlayout.RefreshLazyColumn
import com.a.wanandroid.nav.Navigation
import com.a.wanandroid.net.bean.Article
import com.a.wanandroid.screen.main.base.ArticleItem
import com.a.wanandroid.screen.main.index.BannerViewPager
import com.a.wanandroid.screen.main.base.ScreenLoading

@Composable
fun MainIndexScreen(
    viewModel: MainIndexViewModel = viewModel(),
    goDetail: ((Article) -> Unit)? = null
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
                uiState.topArticles.forEach {
                    item {
                        ArticleItem(it, true, onClick = goDetail)
                    }
                }
                uiState.articles.forEach {
                    item {
                        ArticleItem(it, false, onClick = goDetail)
                    }
                }
            }
        }
    }
}
