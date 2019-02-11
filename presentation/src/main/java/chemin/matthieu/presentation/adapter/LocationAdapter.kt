package chemin.matthieu.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import chemin.matthieu.entities.Location
import chemin.matthieu.presentation.R

class LocationAdapter(val layoutInflater: LayoutInflater) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    class ViewHolder(view: View, private val onItemClickListener: OnItemClickListener?): RecyclerView.ViewHolder(view) {
        var locationId: Long = 0
        val locationNameTextView: TextView
        val locationCountryTextView: TextView
        val locationFavoredImageView: ImageView
        init {
            locationNameTextView = view.findViewById(R.id.row_location_name)
            locationCountryTextView = view.findViewById(R.id.row_location_country)
            locationFavoredImageView = view.findViewById(R.id.row_location_favored)
            locationFavoredImageView.setOnClickListener {
                onItemClickListener?.onFavoredLocationClick(locationId)
            }
        }
    }

    interface OnItemClickListener {
        fun onFavoredLocationClick(locationId: Long)
    }

    var onItemClickListener: OnItemClickListener? = null

    var locationList: List<Location>? = null

    override fun getItemCount() = locationList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.row_location, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        locationList?.get(position)?.let {
            holder.locationId = it.id
            holder.locationNameTextView.text = it.name
            holder.locationCountryTextView.text = it.country
            holder.locationFavoredImageView.setImageResource(
                    if (it.favored) { android.R.drawable.star_big_on } else { android.R.drawable.star_big_off }
            )
        }
    }
}