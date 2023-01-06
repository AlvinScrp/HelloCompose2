package com.example.androidview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AndroidViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        setContentView(R.layout.activity_android_view)
        findViewById<View>(R.id.btnTo2).setOnClickListener {
            startActivity(Intent(context,AndroidViewActivity2::class.java))
        }
    }
}