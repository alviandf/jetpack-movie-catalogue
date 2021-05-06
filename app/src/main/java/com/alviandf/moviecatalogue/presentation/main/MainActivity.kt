package com.alviandf.moviecatalogue.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.alviandf.moviecatalogue.R.id
import com.alviandf.moviecatalogue.R.layout
import kotlinx.android.synthetic.main.activity_main.navView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val appBarConfiguration = AppBarConfiguration.Builder(
            id.navMovie,
            id.navTv,
            id.navFavorite
        ).build()

        val navController = Navigation.findNavController(this,
            id.navHostFragment
        )

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
    }
}