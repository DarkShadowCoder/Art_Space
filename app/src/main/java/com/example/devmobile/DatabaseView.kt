package com.example.devmobile

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.launch

@Database(entities = [Airport::class, Favorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun airportDao(): AirportDao
}

class MainViewModel(private val dao: AirportDao, private val context: Context) : ViewModel() {
    var query by mutableStateOf("")
    var searchResults by mutableStateOf(listOf<Airport>())
    var favorites by mutableStateOf(listOf<Favorite>())

    init {
        viewModelScope.launch {
            query = loadQuery(context)
            searchAirports()
        }
    }

    fun searchAirports() {
        if (query.isEmpty()) {
            favorites = dao.getFavorites()
        } else {
            searchResults = dao.searchAirports("%$query%")
        }
    }

    fun addFavorite(origin: String, destination: String) {
        dao.addFavorite(Favorite(departure_code = origin, destination_code = destination))
        searchAirports()
    }

    fun saveQueryState() {
        viewModelScope.launch {
            saveQuery(context, query)
        }
    }
}
