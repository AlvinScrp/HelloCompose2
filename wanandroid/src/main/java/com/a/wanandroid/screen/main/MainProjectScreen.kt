package com.a.wanandroid.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a.wanandroid.component.refreshlayout.RefreshLazyColumn
import com.a.wanandroid.screen.main.base.ProjectArticleItem
import com.a.wanandroid.screen.main.base.ScreenLoading

@Composable
fun MainProjectScreen(viewModel: MainProjectViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState().value
    val refreshState = viewModel.refreshState.collectAsState().value
    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.loading) {
            ScreenLoading()
        } else {

            RefreshLazyColumn(state = refreshState,
                onRefresh = { viewModel.refreshData() },
                onLoadMore = { viewModel.loadMore() }) {
                uiState.articles.forEach { item { ProjectArticleItem(it) } }
            }
        }
    }
}