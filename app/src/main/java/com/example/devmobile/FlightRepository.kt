package com.example.devmobile

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface FlightRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllAirportStream(): Flow<List<Airport>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getAirportStream(value: Int): Flow<Airport?>

    /**
     * Insert item in the data source
     */
    suspend fun insertFavorite(item: Favorite)

}