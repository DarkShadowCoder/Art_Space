package com.example.devmobile

import androidx.room.Insert
import java.util.concurrent.Flow

interface FlightDao {
    @Insert(onConflict = onConflictStrategy.IGNORE)
    suspend fun insert(favorite: Favorite)

    @Query("SELECT * From airport WHERE iata_code = :code or name = :name")
    fun getAirport(code: Int, name : String) : Flow<Airport>

    @Query("SELECT * From airport")
    fun getAllAirport() : Flow<Airport>
}