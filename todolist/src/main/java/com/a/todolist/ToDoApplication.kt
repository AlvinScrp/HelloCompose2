package com.a.todolist

import android.app.Application

/**
 * @Author: zhutao
 * @desc:
 */

class ToDoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppHolder.appContext = this
    }
}
object AppHolder {
    const val SPLASH_DELAY = 3000L
    lateinit var appContext: Application
}