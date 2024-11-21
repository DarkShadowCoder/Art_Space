package com.example.devmobile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "iata_code") val iata_code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "passengers") val passengers: Int,
)
@Entity(tabelName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "departure_code") val departure_code: String,
    @ColumnInfo(name = "destination_code") val destination_code: String,
)