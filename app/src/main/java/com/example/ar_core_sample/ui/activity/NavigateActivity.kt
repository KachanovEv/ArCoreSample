package com.example.ar_core_sample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ar_core_sample.R
import kotlinx.android.synthetic.main.activity_navigate.*


class NavigateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigate)

        val navController = findNavController(R.id.nav_host_fragment)
        navController.popBackStack(R.id.nav_host_fragment, true)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_camera,
                R.id.navigation_library
            )
        )

        nav_view.setupWithNavController(navController)
    }
}
