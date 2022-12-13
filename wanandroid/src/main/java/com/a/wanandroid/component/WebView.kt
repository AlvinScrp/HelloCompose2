package com.a.wanandroid.component

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun WebViewContent(url: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                this.webViewClient = object :WebViewClient(){
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        Log.d("alvin","UrlLoading:${url}")
                        return super.shouldOverrideUrlLoading(view, url)
                    }
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }
                this.loadUrl(url)

            }
        })
}