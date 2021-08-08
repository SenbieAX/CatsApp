package com.strelkovax.catsapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.strelkovax.catsapp.R
import com.strelkovax.catsapp.presentation.screens.favorite.FragmentFavoriteList
import com.strelkovax.catsapp.presentation.screens.main.FragmentList

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_main -> {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            R.id.item_favorite -> {
                if (supportFragmentManager.findFragmentByTag("fragment-favorite") == null) {
                    supportFragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, FragmentFavoriteList(), "fragment-favorite")
                        .addToBackStack("fragment-favorite")
                        .commit()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, FragmentList(), "fragment-list")
                .commit()
        }
    }
}