package com.example.sushiveslatestapp.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import androidx.drawerlayout.widget.DrawerLayout

class MyDrawerLayout(ctx: Context, attrs: AttributeSet) : DrawerLayout(ctx, attrs) {
    var isSwipeOpenEnabled: Boolean = false

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (!isSwipeOpenEnabled && !isDrawerVisible(Gravity.LEFT)) {
            return false
        }
        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (!isSwipeOpenEnabled && isDrawerVisible(Gravity.LEFT)) {
            return false
        }
        return super.onTouchEvent(ev)
    }
}