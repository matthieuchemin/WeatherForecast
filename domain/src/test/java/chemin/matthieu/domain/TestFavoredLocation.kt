package chemin.matthieu.domain

import chemin.matthieu.commontesttools.mock
import chemin.matthieu.entities.Location
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TestFavoredLocation {

    private val locations = listOf(
            Location(75, "Paris", "FR", false),
            Location(69, "Lyon", "FR", true),
            Location(13, "Marseille", "FR", false)
    )
    private val locationId = 69L
    private val search = "some_search"
    private lateinit var locationRepository: FavoredLocation.LocationRepository
    private lateinit var favoredLocation: FavoredLocation

    @Before
    fun setUp() {
        locationRepository = mock()
        Mockito.`when`(locationRepository.searchLocation(search)).thenReturn(locations)
        Mockito.`when`(locationRepository.favoredLocation(locationId, true)).then {  }

        favoredLocation = FavoredLocation(locationRepository)
    }

    @Test
    fun test_favoredLocation() {
        val list = favoredLocation.perform(Pair(locationId, search))

        Assert.assertEquals(locations, list)
        Mockito.verify(locationRepository, Mockito.times(1)).favoredLocation(locationId, true)
        Mockito.verify(locationRepository, Mockito.times(1)).searchLocation(search)
    }


}