package data

import com.example.devmobile.Airport
import com.example.devmobile.Favorite
import kotlinx.coroutines.flow.Flow
import com.example.devmobile.FlightDao
import com.example.devmobile.FlightRepository


class OfflineFlightRepository(private val flightDao: FlightDao) : FlightRepository {
    override fun getAllAirportStream(): Flow<List<Airport>> = flightDao.getAllAirport()

    override fun getAirportStream(code : Int, name: String): Flow<Airport?> = flightDao.getAirport(code, name)

    override suspend fun insertItem(favorite: Favorite) = flightDao.insert(favorite)

}