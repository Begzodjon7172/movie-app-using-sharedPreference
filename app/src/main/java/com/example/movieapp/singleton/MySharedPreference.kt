package com.example.movieapp.singleton

import android.content.Context
import android.content.SharedPreferences
import com.example.movieapp.models.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME = "Movie"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var movieList: ArrayList<Movie>
        get() = jsonStringToList(preferences.getString("movie", "[]")!!)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("movie", listToJsonString(value))
            }
        }

    private fun listToJsonString(arrayList: ArrayList<Movie>): String {
        return Gson().toJson(arrayList)
    }

    private fun jsonStringToList(gsonString: String): ArrayList<Movie> {
        val typeToken = object : TypeToken<ArrayList<Movie>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}