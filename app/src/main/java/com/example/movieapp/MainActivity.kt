package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.adapters.MovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.models.Movie
import com.example.movieapp.singleton.MySharedPreference

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)
        }

        binding.showBtn.setOnClickListener {
            val intent = Intent(this, ListMovieActivity::class.java)
            startActivity(intent)
        }

    }

}