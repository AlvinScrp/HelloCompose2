package com.a.wanandroid

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.a.wanandroid.net.RetrofitHelper

class MainApp : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .okHttpClient(RetrofitHelper.getOkHttpClient()).build()
    }
}