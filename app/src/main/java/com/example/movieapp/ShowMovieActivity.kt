package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityShowMovieBinding
import com.example.movieapp.singleton.MySharedPreference

class ShowMovieActivity : AppCompatActivity() {

    private val binding: ActivityShowMovieBinding by lazy {
        ActivityShowMovieBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        MySharedPreference.init(this)
        val movieList = MySharedPreference.movieList

        val position = intent.getIntExtra("position", 0)

        supportActionBar?.title = movieList[position].movieName

        binding.apply {
            aboutTv.text = "About movie: ${movieList[position].aboutMovie}"
            nameTv.text = "Movie name: ${movieList[position].movieName}"
            authorsTv.text = "Authors: ${movieList[position].authorList}"
            dateTv.text = "Date: ${movieList[position].date}"

            closeBtn.setOnClickListener {
                finish()
            }
        }


    }
}