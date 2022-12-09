package com.a.wanandroid.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a.wanandroid.component.refreshlayout.RefreshLazyColumnState
import com.a.wanandroid.net.RetrofitHelper
import com.a.wanandroid.net.bean.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainWxArticleUiState(
    var articles: List<Article> = emptyList(),
    var loading: Boolean = false,
)

class MainWxArticleViewModel : ViewModel() {

    val uiState = MutableStateFlow(MainWxArticleUiState(loading = true))
    val refreshState =
        MutableStateFlow(RefreshLazyColumnState(isRefreshing = false, enableLoadMore = false))
    var pageNum = 0
    private val api by lazy { RetrofitHelper.service }

    init {
        initData()
    }

    private fun initData(isRefreshing: Boolean = false) {
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
            var chapters = api.getWxChapters().data.orEmpty()
            val articles = mutableListOf<Article>()

            chapters.subList(0, 5).map {
                Log.d("alvin", "async")
                async { api.getWxArticles(it.id, pageNum) }
            }.forEach {
                Log.d("alvin", "await")
                articles.addAll(it.await().data?.datas.orEmpty())
            }
          articles.sortByDescending { it.publishTime }

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
        initData(isRefreshing = true)
    }

}
