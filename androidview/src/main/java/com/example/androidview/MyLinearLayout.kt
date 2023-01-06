package com.example.androidview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

class MyLinearLayout :LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d("alvin","MyLinearLayout onMeasure")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.d("alvin","MyLinearLayout onLayout")
    }


}