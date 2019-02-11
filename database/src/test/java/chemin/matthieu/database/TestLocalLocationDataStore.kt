package chemin.matthieu.database

import chemin.matthieu.commontesttools.mock
import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.database.datastore.LocalLocationDataStore
import chemin.matthieu.database.entities.LocationDatabaseEntity
import chemin.matthieu.database.location.LocationWriter
import chemin.matthieu.entities.Location
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class TestLocalLocationDataStore {

    private lateinit var locationDao: LocationDao
    private lateinit var locationWriter: LocationWriter
    private lateinit var localLocationDataStore: LocalLocationDataStore

    @Test
    fun test_getLocationName_nullResponseFromDao() {
        val locationId = 105L
        locationWriter = mock()
        locationDao = mock()
        Mockito.`when`(locationDao.getLocationCityNameWithId(locationId)).thenReturn(null)
        localLocationDataStore = LocalLocationDataStore(locationDao, locationWriter)

        val result = localLocationDataStore.getLocationName(locationId)
        Assert.assertEquals("-", result)
        Mockito.verifyZeroInteractions(locationWriter)
        Mockito.verify(locationDao, Mockito.times(1)).getLocationCityNameWithId(locationId)
    }

    @Test
    fun test_getLocationName_withResponseFromDao() {
        val locationId = 105L
        val locationName = "Arèches"
        locationWriter = mock()
        locationDao = mock()
        Mockito.`when`(locationDao.getLocationCityNameWithId(locationId)).thenReturn(locationName)
        localLocationDataStore = LocalLocationDataStore(locationDao, locationWriter)

        val result = localLocationDataStore.getLocationName(locationId)
        Assert.assertEquals(locationName, result)
        Mockito.verifyZeroInteractions(locationWriter)
        Mockito.verify(locationDao, Mockito.times(1)).getLocationCityNameWithId(locationId)
    }

    @Test
    fun test_favoredLocation() {
        val locationId = 105L
        val favored = true
        locationWriter = mock()
        locationDao = mock()
        localLocationDataStore = LocalLocationDataStore(locationDao, locationWriter)

        localLocationDataStore.favoredLocation(locationId, favored)
        Mockito.verifyZeroInteractions(locationWriter)
        Mockito.verify(locationDao, Mockito.times(1)).favoredLocation(locationId, favored)
    }

    @Test
    fun test_searchLocations_emptyDb() {
        val search = "Gilly-sur-Isère"
        locationWriter = mock()
        locationDao = mock()
        Mockito.`when`(locationDao.countLocation()).thenReturn(0)
        Mockito.`when`(locationDao.searchLocation("%$search%")).thenReturn(emptyList())
        localLocationDataStore = LocalLocationDataStore(locationDao, locationWriter)

        val result = localLocationDataStore.searchFavoredLocations(search)
        Assert.assertEquals(0, result.size)
        Mockito.verify(locationWriter, Mockito.times(1)).writeLocationToDatabase()
        Mockito.verify(locationDao, Mockito.times(1)).searchLocation("%$search%")
    }

    @Test
    fun test_searchLocations_growingDb() {
        val search = "Gilly-sur-Isère"
        locationWriter = mock()
        locationDao = mock()
        Mockito.`when`(locationDao.countLocation()).thenReturn(0)
        Mockito.`when`(locationDao.searchLocation("%$search%")).thenReturn(
                listOf(
                        LocationDatabaseEntity(73, search, null, true),
                        LocationDatabaseEntity(74, "Annecy", "FR", false)
                )
        )
        localLocationDataStore = LocalLocationDataStore(locationDao, locationWriter)

        val result = localLocationDataStore.searchFavoredLocations(search)
        Assert.assertEquals(2, result.size)
        Mockito.verify(locationWriter, Mockito.times(1)).writeLocationToDatabase()
        Mockito.verify(locationDao, Mockito.times(1)).searchLocation("%$search%")
        Assert.assertEquals(Location(73, search, "-", true), result[0])
        Assert.assertEquals(Location(74, "Annecy", "FR", false), result[1])
    }

    @Test
    fun test_searchLocations_NotEmptyDb() {
        val search = "Gilly-sur-Isère"
        locationWriter = mock()
        locationDao = mock()
        Mockito.`when`(locationDao.countLocation()).thenReturn(10)
        Mockito.`when`(locationDao.searchLocation("%$search%")).thenReturn(
                listOf(
                        LocationDatabaseEntity(73, search, null, true),
                        LocationDatabaseEntity(74, "Annecy", "FR", false)
                )
        )
        localLocationDataStore = LocalLocationDataStore(locationDao, locationWriter)

        val result = localLocationDataStore.searchFavoredLocations(search)
        Assert.assertEquals(2, result.size)
        Mockito.verifyZeroInteractions(locationWriter)
        Mockito.verify(locationDao, Mockito.times(1)).searchLocation("%$search%")
        Assert.assertEquals(Location(73, search, "-", true), result[0])
        Assert.assertEquals(Location(74, "Annecy", "FR", false), result[1])
    }

    // TODO add the same tests for getFavoredLocations method
}