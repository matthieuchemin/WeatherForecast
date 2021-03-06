package chemin.matthieu.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chemin.matthieu.commontools.exhaustive
import chemin.matthieu.commontools.w
import chemin.matthieu.entities.Location
import chemin.matthieu.presentation.R
import chemin.matthieu.presentation.adapter.LocationAdapter
import chemin.matthieu.presentation.viewmodel.FavoredLocationViewModel
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "FavoredLocationsActivity"

class FavoredLocationsActivity : DaggerAppCompatActivity(), LocationAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var locationAdapter: LocationAdapter

    private lateinit var favoredLocationViewModel: FavoredLocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationAdapter.onItemClickListener = this

        setContentView(R.layout.activity_favored_location)
        val recyclerView: RecyclerView = findViewById(R.id.activity_favored_location_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = locationAdapter

        setTitle(R.string.activity_favored_title)

        favoredLocationViewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoredLocationViewModel::class.java)
        favoredLocationViewModel.favoredLocation.observe(this, Observer { showLocation(it) })
    }

    override fun onResume() {
        super.onResume()
        favoredLocationViewModel.readFavoredLocation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_favored_locations_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                R.id.activity_favored_location_menu_add -> {
                    val intent = Intent(this, SearchLocationsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }.exhaustive

    private fun showLocation(locations: List<Location>) {
        locationAdapter.locationList = locations
        locationAdapter.notifyDataSetChanged()
    }

    override fun onFavoredLocationClick(locationId: Long) {
        Toast.makeText(this, "You should not be able to see location that are not favored here !!", Toast.LENGTH_SHORT).show()
        Timber.w(TAG, "Location that is not favored present in favored activity ?!")
    }

    override fun onUnFavoredLocationClick(locationId: Long) = favoredLocationViewModel.unfavoredLocation(locationId)

    override fun onLocationClick(locationId: Long) {
        val intent = Intent(this, ForecastActivity::class.java)
        intent.putExtra(ForecastActivity.LOCATION_ID, locationId)
        startActivity(intent)
    }
}