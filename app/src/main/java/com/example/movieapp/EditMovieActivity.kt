package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.adapters.MovieAdapter
import com.example.movieapp.databinding.ActivityEditMovieBinding
import com.example.movieapp.models.Movie
import com.example.movieapp.singleton.MySharedPreference

class EditMovieActivity : AppCompatActivity() {

    private val binding: ActivityEditMovieBinding by lazy {
        ActivityEditMovieBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Edit movie"

        MySharedPreference.init(this)
        val movieList = MySharedPreference.movieList

        val position = intent.getIntExtra("position", 0)
        binding.apply {
            edtAbout.setText(movieList[position].aboutMovie)
            edtName.setText(movieList[position].movieName)
            edtAuthors.setText(movieList[position].authorList)

            saveBtn.setOnClickListener {
                if (isValid()) {
                    val name = edtName.text.toString()
                    val authors = edtAuthors.text.toString()
                    val about = edtAbout.text.toString()
                    val movie = Movie(name, authors, about)

                    movieList[position].movieName = name
                    movieList[position].authorList = authors
                    movieList[position].aboutMovie = about
                    movieList[position].date = movie.date

                    MySharedPreference.movieList = movieList
                    Toast.makeText(this@EditMovieActivity, "O'zgartirildi", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            }
        }
    }

    private fun isValid(): Boolean {
        return if (binding.edtName.text.toString() == "") {
            Toast.makeText(this, "Kino nomi kiritilmagan", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.edtAuthors.text.toString() == "") {
            Toast.makeText(this, "Avtorlar nomi kiritilmagan", Toast.LENGTH_SHORT).show()
            false
        } else if (binding.edtAbout.text.toString() == "") {
            Toast.makeText(this, "Kino haqida ma'lumot kiritilmagan", Toast.LENGTH_SHORT).show()
            false
        } else true
    }
}