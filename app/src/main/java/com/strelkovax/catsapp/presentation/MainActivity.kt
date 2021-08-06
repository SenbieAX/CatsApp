package com.strelkovax.catsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.strelkovax.catsapp.R
import com.strelkovax.catsapp.presentation.screens.main.FragmentList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fm = supportFragmentManager
        fm.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragment_container_view, FragmentList())
            .commit()
    }
}