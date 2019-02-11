package chemin.matthieu.database.location

import android.util.JsonReader
import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.database.entities.LocationDatabaseEntity
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.StringReader

private const val JSON = """[
	{
		"id": 3017680,
		"name": "Forville",
		"country": "FR"
	},
	{
		"id": 3007202,
		"name": "La Portanière",
		"country": "FR"
	},
	{
		"id": 6441676,
		"name": "Wittenheim",
		"country": "FR"
	}
]"""


class TestLocationJsonReader {

    private lateinit var jsonReader: JsonReader
    private lateinit var locationDao: LocationDao
    private lateinit var locationJsonReader: LocationJsonReader

    @Before
    fun setUp() {
        jsonReader = JsonReader(StringReader(JSON))
        locationDao = Mockito.mock(LocationDao::class.java)
        locationJsonReader = LocationJsonReader(locationDao)
    }

    @Test
    fun test_jsonReading() {
        locationJsonReader.readLocationArray(jsonReader)

        Mockito.verify(locationDao, Mockito.times(1)).insert(
                LocationDatabaseEntity(3017680, "Forville", "FR")
        )
        Mockito.verify(locationDao, Mockito.times(1)).insert(
                LocationDatabaseEntity(3007202, "La Portanière", "FR")
        )
        Mockito.verify(locationDao, Mockito.times(1)).insert(
                LocationDatabaseEntity(6441676, "Wittenheim", "FR")
        )
        Mockito.verifyNoMoreInteractions(locationDao)
    }
}