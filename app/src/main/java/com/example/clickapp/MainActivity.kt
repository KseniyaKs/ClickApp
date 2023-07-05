package com.example.clickapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findNavController(R.id.nav_host_fragment)
    }
}