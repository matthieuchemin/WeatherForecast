package chemin.matthieu.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.entities.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoredLocationViewModel(private val readFavoredLocation: ReadFavoredLocation) : ScopedViewModel() {

    private val _favoredLocations = MutableLiveData<List<Location>>()
    val favoredLocation: LiveData<List<Location>>
        get() = _favoredLocations

    fun readFavoredLocation() {
        launch {
            val listLocations = withContext(Dispatchers.IO) {
                readFavoredLocation.perform(0)
            }
            _favoredLocations.value = listLocations
        }
    }

}