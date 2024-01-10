package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.movieapp.adapters.MovieAdapter
import com.example.movieapp.databinding.ActivityListMovieBinding
import com.example.movieapp.models.Movie
import com.example.movieapp.singleton.MySharedPreference

class ListMovieActivity : AppCompatActivity() {

    private val binding: ActivityListMovieBinding by lazy {
        ActivityListMovieBinding.inflate(
            layoutInflater
        )
    }

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = "Movies"
        
    }

    override fun onResume() {
        super.onResume()
        MySharedPreference.init(this)

        val movieList = MySharedPreference.movieList

        movieAdapter = MovieAdapter(movieList, object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movie: Movie, position: Int) {
                val intent = Intent(this@ListMovieActivity, ShowMovieActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }

            override fun onItemEditClick(movie: Movie, position: Int) {
                val intent = Intent(this@ListMovieActivity, EditMovieActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }

            override fun onItemDeleteClick(movie: Movie, position: Int) {
                movieList.removeAt(position)
                MySharedPreference.movieList = movieList
                movieAdapter.notifyItemRemoved(position)
                movieAdapter.notifyItemRangeChanged(position, movieList.size)
            }
        })

        binding.rv.adapter = movieAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                val intent = Intent(this, AddMovieActivity::class.java)
                startActivity(intent)
                true
            }

            else -> false
        }
    }

}