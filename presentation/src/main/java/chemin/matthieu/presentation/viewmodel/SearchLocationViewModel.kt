package chemin.matthieu.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chemin.matthieu.domain.FavoredLocation
import chemin.matthieu.domain.SearchLocation
import chemin.matthieu.entities.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchLocationViewModel(
        private val searchLocation: SearchLocation,
        private val favoredLocation: FavoredLocation
) : ScopedViewModel() {

    private var search: String = ""

    private val _searchResults = MutableLiveData<List<Location>>()
    val searchResult: LiveData<List<Location>>
        get() = _searchResults

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private var currentJob: Job? = null

    fun search(search: String) {
        this.search = search
        currentJob?.cancel()
        currentJob = launch {
            _loading.value = true
            val results = withContext(Dispatchers.IO) {
                searchLocation.perform(search)
            }
            _searchResults.value = results
            _loading.value = false
            currentJob = null
        }
    }

    fun favoredLocation(locationId: Long) {
        launch {
            val results = withContext(Dispatchers.IO) {
                favoredLocation.perform(Pair(locationId, search))
            }
            _searchResults.value = results
        }
    }

}