package chemin.matthieu.presentation.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
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

class SearchLocationsActivity : DaggerAppCompatActivity(), TextWatcher {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var locationAdapter: LocationAdapter

    private lateinit var searchLocationViewModel: SearchLocationViewModel

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search_location)
        val recyclerView: RecyclerView = findViewById(R.id.activity_search_location_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = locationAdapter
        val editText: EditText = findViewById(R.id.activity_search_location_edit_text)
        editText.addTextChangedListener(this)
        progressBar = findViewById(R.id.activity_search_location_progress)

        setTitle(R.string.activity_search_title)

        searchLocationViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchLocationViewModel::class.java)
        searchLocationViewModel.searchResult.observe(this, Observer { showLocation(it) })
        searchLocationViewModel.loading.observe(this, Observer { showLoading(it) })
    }

    private fun showLocation(locations: List<Location>) {
        locationAdapter.locationList = locations
        locationAdapter.notifyDataSetChanged()
    }

    private fun showLoading(loading: Boolean) {
        progressBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
    }

    override fun afterTextChanged(p0: Editable?) {
        val content = p0?.toString() ?: ""
        searchLocationViewModel.search(content)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // nothing to be done
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // nothing to be done
    }
}