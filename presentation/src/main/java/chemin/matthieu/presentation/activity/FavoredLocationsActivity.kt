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
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class FavoredLocationsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var locationAdapter: LocationAdapter

    private lateinit var favoredLocationViewModel: FavoredLocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favored_location)
        val recyclerView: RecyclerView = findViewById(R.id.activity_favored_location_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = locationAdapter

        setTitle(R.string.activity_favored_title)

        favoredLocationViewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoredLocationViewModel::class.java)
        favoredLocationViewModel.favoredLocation.observe(this, Observer { showLocation(it) })
        favoredLocationViewModel.readFavoredLocation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_favored_locations_menu, menu)
        return true
    }

    private fun showLocation(locations: List<Location>) {
        locationAdapter.locationList = locations
        locationAdapter.notifyDataSetChanged()
    }
}