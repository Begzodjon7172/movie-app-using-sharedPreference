package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.databinding.ActivityAddMovieBinding
import com.example.movieapp.models.Movie
import com.example.movieapp.singleton.MySharedPreference

class AddMovieActivity : AppCompatActivity() {
    private val binding: ActivityAddMovieBinding by lazy {
        ActivityAddMovieBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Add movie"

        MySharedPreference.init(this)
        val movieList = MySharedPreference.movieList

        binding.apply {
            saveBtn.setOnClickListener {
                if (isValid()) {
                    val name = edtName.text.toString().trim()
                    val authors = edtAuthors.text.toString().trim()
                    val about = edtAbout.text.toString().trim()

                    movieList.add(Movie(name, authors, about))
                    MySharedPreference.movieList = movieList

                    Toast.makeText(this@AddMovieActivity, "Saqlandi!!!", Toast.LENGTH_SHORT).show()
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