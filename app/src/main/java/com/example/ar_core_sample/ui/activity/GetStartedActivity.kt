package com.example.ar_core_sample.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ar_core_sample.R
import kotlinx.android.synthetic.main.activity_get_started.*

class GetStartedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        btnGetStarted.setOnClickListener {
            val intent = Intent(this, NavigateActivity::class.java)
            startActivity(intent)
        }
    }
}