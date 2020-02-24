package com.richyeoh.android.dawdler

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.MotionEvent
import android.view.View

var View.lastClickTime: Long
    get() = getTag(hashCode()) as? Long ?: 0
    set(value) = setTag(hashCode(), value)

inline fun View.singleClick(delayMillis: Long = 1500, crossinline action: (View) -> Unit) {
    setOnClickListener {
        val nowTime = System.currentTimeMillis()
        if (nowTime - lastClickTime >= delayMillis) {
            lastClickTime = System.currentTimeMillis()
            action(this@singleClick)
        }
    }
}

inline fun View.maskClick(delayMillis: Long, crossinline action: (View) -> Unit) {
    var startMillis = 0L

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val nowMillis = System.currentTimeMillis()
            if (nowMillis - startMillis >= delayMillis) {
                action(this@maskClick)
            } else {
                sendEmptyMessageDelayed(0, delayMillis)
            }
        }
    }

    setOnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startMillis = System.currentTimeMillis()
                handler.sendEmptyMessage(0)
            }

            MotionEvent.ACTION_UP -> {
                handler.removeMessages(0)
                val endMillis = System.currentTimeMillis()
                //如果endMillis-startMillis>=delayMills拦截点击事件
                if (endMillis - startMillis >= delayMillis) {
                    return@setOnTouchListener true
                }
                startMillis = System.currentTimeMillis()
            }
        }
        //如果没有设置任何的ListenerInfo,则返回true,否则返回false
        //因为没有设置任何ListenerInfo,返回false的话不会有MotionEvent.ACTION_UP事件产生
        !hasOnClickListeners()
    }
}
