package data

import com.example.devmobile.AppDatabase
import com.example.devmobile.FlightRepository

override val flightsRepository: FlightRepository by lazy {
    OfflineFlightRepository (AppDatabase.getDatabase(context).flightDao())
}