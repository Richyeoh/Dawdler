package com.richyeoh.android.app.dawdler

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.richyeoh.android.dawdler.ActivityOps

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSecond(view: View) {
        ActivityOps.startActivity(SecondActivity::class.java)
    }
}
