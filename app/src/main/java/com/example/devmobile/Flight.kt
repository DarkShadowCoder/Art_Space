package com.example.devmobile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate= true)
    val id: Int = 0,
    @ColumnInfo(name = "iata_code")
    val iata_code: String,
    val name: String,
    val passengers: Int
)

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "departure_code")
    val departure_code: String,
    @ColumnInfo(name = "destination_code")
    val destination_code: String
)
