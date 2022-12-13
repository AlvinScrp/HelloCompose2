package com.a.wanandroid.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a.wanandroid.component.refreshlayout.RefreshLazyColumnState
import com.a.wanandroid.net.RetrofitHelper
import com.a.wanandroid.net.bean.Article
import com.a.wanandroid.net.bean.Banner
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainProjectUiState(
    var articles: List<Article> = emptyList(),
    var loading: Boolean = false,
    )

class MainProjectViewModel : ViewModel() {

    val uiState = MutableStateFlow(MainProjectUiState(loading = true))
    val refreshState = MutableStateFlow(RefreshLazyColumnState(isRefreshing = false))
    var pageNum = 0
    private val api by lazy { RetrofitHelper.service }

    init {
        loadData()
    }

    private fun loadData(isRefreshing: Boolean = false) {
        pageNum = 0
        if (isRefreshing) {
            refreshState.update { it.copy(isRefreshing = true) }
        } else {
            uiState.update { it.copy(loading = true) }
        }
        viewModelScope.launch {
            if (isRefreshing) {
                delay(2000L)
            }
            var articles = api.getProjectArticles(pageNum)?.data?.datas.orEmpty()

            uiState.update {
                it.copy(
                    loading = false,
                    articles = articles
                )
            }
            refreshState.update {
                it.copy(isRefreshing = false, isLoadingMore = false, hasMore = true)
            }
        }

    }

    fun refreshData() {
        loadData(isRefreshing = true)
    }

    fun loadMore() {
        if (!refreshState.value.hasMore) {
            return
        }
        refreshState.update {
            it.copy(isRefreshing = false, isLoadingMore = true, hasMore = true)
        }
        pageNum++
        viewModelScope.launch {
            delay(2000L)
            var response = api.getProjectArticles(pageNum)?.data
            response?.datas?.takeIf {
                it.isNullOrEmpty().not()
            }?.let { list ->
                uiState.update { state ->
                    state.copy(articles = state.articles + list)
                }
            }
            refreshState.update {
                it.copy(
                    isLoadingMore = false,
                    hasMore = response?.over == false
                )
            }
        }
    }

}
