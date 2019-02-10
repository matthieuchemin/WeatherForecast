package chemin.matthieu.networking.datastore

import chemin.matthieu.entities.Forecast
import chemin.matthieu.repositories.ForecastRepository

class RemoteForecastDataStore : ForecastRepository.RemoteForecastDataStore {

    override fun getForecastForCityName(cityName: String): Array<Forecast> {
        TODO()
    }
}