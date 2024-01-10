package com.example.movieapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.ShowMovieActivity
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.models.Movie

class MovieAdapter(
    private val movieList: ArrayList<Movie>,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MovieAdapter.VH>() {

    inner class VH(private val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun onBind(movie: Movie, position: Int) {
            itemMovieBinding.movieNameTv.text = movie.movieName
            itemMovieBinding.authorsTv.text = movie.authorList
            itemMovieBinding.dateTv.text = movie.date

            itemMovieBinding.root.setOnClickListener{
                itemClickListener.onItemClick(movie, position)
            }

            itemMovieBinding.deleteBtn.setOnClickListener {
                itemClickListener.onItemDeleteClick(movie, position)
            }

            itemMovieBinding.editBtn.setOnClickListener {
                itemClickListener.onItemEditClick(movie, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(movieList[position], position)
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie, position: Int)
        fun onItemEditClick(movie: Movie, position: Int)
        fun onItemDeleteClick(movie: Movie, position: Int)
    }
}