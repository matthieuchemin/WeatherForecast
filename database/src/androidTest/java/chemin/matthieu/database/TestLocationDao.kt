package chemin.matthieu.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.database.entities.LocationDatabaseEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestLocationDao {

    private lateinit var locationDao: LocationDao
    private lateinit var weatherDatabase: WheatherForecastDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        weatherDatabase = Room.inMemoryDatabaseBuilder(
                context,
                WheatherForecastDatabase::class.java
        ).build()
        locationDao = weatherDatabase.locationDao()
    }

    @After
    fun tearDown() {
        weatherDatabase.close()
    }

    @Test
    fun test_getLocationCityName_emptyDb() {
        val name = locationDao.getLocationCityNameWithId(105L)
        Assert.assertNull(name)
    }

    @Test
    fun test_getLocationCityName_noMatchingEntity() {
        val locationDatabaseEntity = LocationDatabaseEntity(
                id = 1,
                cityName = "Marseille",
                country = "FR",
                favored = false
        )
        locationDao.insert(locationDatabaseEntity)

        val name = locationDao.getLocationCityNameWithId(105L)
        Assert.assertNull(name)
    }

    @Test
    fun test_getLocationCityName_matchingEntity() {
        val locationDatabaseEntity = LocationDatabaseEntity(
                id = 13,
                cityName = "Marseille",
                country = "FR",
                favored = false
        )
        locationDao.insert(locationDatabaseEntity)

        val name = locationDao.getLocationCityNameWithId(13)
        Assert.assertEquals(locationDatabaseEntity.cityName, name)
    }

    @Test
    fun test_getFavoredLocation_emptyDb() {
        val favored = locationDao.getFavoredLocation()
        Assert.assertEquals(0, favored.size)
    }

    @Test
    fun test_getFavoredLocation_noMatchingEntity() {
        val locationDatabaseEntity = LocationDatabaseEntity(
                id = 13,
                cityName = "Marseille",
                country = "FR",
                favored = false
        )
        locationDao.insert(locationDatabaseEntity)

        val favored = locationDao.getFavoredLocation()
        Assert.assertEquals(0, favored.size)
    }

    @Test
    fun test_getFavoredLocation_oneMatchingEntity() {
        val locationDatabaseEntity = LocationDatabaseEntity(
                id = 5,
                cityName = "La Grave",
                country = "FR",
                favored = true
        )
        locationDao.insert(locationDatabaseEntity)

        val favored = locationDao.getFavoredLocation()
        Assert.assertEquals(1, favored.size)
        Assert.assertEquals(locationDatabaseEntity, favored[0])
    }

    @Test
    fun test_countLocation_emptyDb() {
        val count = locationDao.countLocation()
        Assert.assertEquals(0, count)
    }

    @Test
    fun test_countLocation_notEmptyDb() {
        val locationDatabaseEntityMarseille = LocationDatabaseEntity(
                id = 13,
                cityName = "Marseille",
                country = "FR",
                favored = false
        )
        val locationDatabaseEntityLaGrave = LocationDatabaseEntity(
                id = 5,
                cityName = "La Grave",
                country = "FR",
                favored = true
        )
        locationDao.insert(locationDatabaseEntityMarseille)
        locationDao.insert(locationDatabaseEntityLaGrave)

        val count = locationDao.countLocation()
        Assert.assertEquals(2, count)
    }

    @Test
    fun test_searchLocation_emptyDb() {
        val results = locationDao.searchLocation("Lyon")
        Assert.assertEquals(0, results.size)
    }

    @Test
    fun test_searchLocation_notEmptyDb() {
        val locationDatabaseEntityLyon = LocationDatabaseEntity(
                id = 69,
                cityName = "Lyon",
                country = "FR",
                favored = true
        )
        val locationDatabaseEntityChambery = LocationDatabaseEntity(
                id = 73,
                cityName = "Chambéry",
                country = "FR",
                favored = true
        )
        val locationDatabaseEntitySainteFoye = LocationDatabaseEntity(
                id = 69457,
                cityName = "Sainte-Foye-lès-Lyon",
                country = "FR",
                favored = false
        )
        locationDao.insert(locationDatabaseEntityLyon)
        locationDao.insert(locationDatabaseEntityChambery)
        locationDao.insert(locationDatabaseEntitySainteFoye)

        val result = locationDao.searchLocation("%Lyon%")
        Assert.assertEquals(2, result.size)
        Assert.assertTrue(result[0].id == 69L && result[1].id == 69457L)
    }

}