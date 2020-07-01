package com.example.learningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.myNavFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    //implementiert den Zurück-Button
    override fun onBackPressed() {
        val navController = this.findNavController(R.id.myNavFragment)
        navController.navigateUp()
    }

    //implementiert den Zurück-Button
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavFragment)
        return navController.navigateUp()
    }
}
