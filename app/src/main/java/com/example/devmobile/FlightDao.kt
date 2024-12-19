package com.example.devmobile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface AirportDao {
    @Query("SELECT * FROM airport WHERE name LIKE :query OR iata_code LIKE :query ORDER BY passengers DESC")
    fun searchAirports(query: String): List<Airport>

    @Query("SELECT * FROM favorite")
    fun getFavorites(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favorite: Favorite)
}
