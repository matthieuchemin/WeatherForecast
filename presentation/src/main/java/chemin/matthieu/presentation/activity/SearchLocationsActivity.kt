package chemin.matthieu.presentation.activity

import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chemin.matthieu.entities.Location
import chemin.matthieu.presentation.R
import chemin.matthieu.presentation.adapter.LocationAdapter
import chemin.matthieu.presentation.viewmodel.FavoredLocationViewModel
import chemin.matthieu.presentation.viewmodel.SearchLocationViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchLocationsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var locationAdapter: LocationAdapter

    private lateinit var searchLocationViewModel: SearchLocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favored_location)
        val recyclerView: RecyclerView = findViewById(R.id.activity_favored_location_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = locationAdapter

        setTitle(R.string.activity_search_title)

        searchLocationViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchLocationViewModel::class.java)
        searchLocationViewModel.searchResult.observe(this, Observer { showLocation(it) })
        searchLocationViewModel.search("Lyon")
    }

    private fun showLocation(locations: List<Location>) {
        locationAdapter.locationList = locations
        locationAdapter.notifyDataSetChanged()
    }
}