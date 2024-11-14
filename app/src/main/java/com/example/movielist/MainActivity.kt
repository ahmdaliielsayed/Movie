package com.example.movielist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.movielist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.appBarHome.toolbar)
        binding.appBarHome.toolbar.visibility = View.GONE
    }
}