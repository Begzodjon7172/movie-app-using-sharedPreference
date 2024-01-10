package com.example.movieapp.models

import java.text.SimpleDateFormat
import java.util.Date

data class Movie(
    var movieName: String,
    var authorList: String,
    var aboutMovie: String,
    var date: String = SimpleDateFormat("dd.MM.yyyy").format(Date())
)
