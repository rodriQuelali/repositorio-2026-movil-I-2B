package com.example.myapplicationproyectomvvmsqlite.ui.Place.list

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationproyectomvvmsqlite.R
import com.example.myapplicationproyectomvvmsqlite.data.local.PlaceEntity
import java.io.File

class PlaceAdapter(
    private val onItemClick: (PlaceEntity) -> Unit
) : ListAdapter<PlaceEntity, PlaceAdapter.PlaceViewHolder>(PlaceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = getItem(position)
        holder.bind(place, onItemClick)
    }

    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPlaceImage: ImageView = itemView.findViewById(R.id.ivPlaceImage)
        private val tvPlaceTitle: TextView = itemView.findViewById(R.id.tvPlaceTitle)
        private val tvPlaceDescription: TextView = itemView.findViewById(R.id.tvPlaceDescription)
        private val tvPlaceLocation: TextView = itemView.findViewById(R.id.tvPlaceLocation)

        fun bind(place: PlaceEntity, onItemClick: (PlaceEntity) -> Unit) {
            tvPlaceTitle.text = place.title
            tvPlaceDescription.text = place.description

            if (place.latitude != null && place.longitude != null) {
                tvPlaceLocation.visibility = View.VISIBLE
                tvPlaceLocation.text = "Lat: %.4f, Lng: %.4f".format(place.latitude, place.longitude)
            } else {
                tvPlaceLocation.visibility = View.GONE
            }

            if (!place.photoPath.isNullOrEmpty()) {
                val file = File(place.photoPath)
                if (file.exists()) {
                    ivPlaceImage.setImageURI(Uri.fromFile(file))
                } else {
                    ivPlaceImage.setImageResource(R.drawable.ic_launcher_background)
                }
            } else {
                ivPlaceImage.setImageResource(R.drawable.ic_launcher_background)
            }

            itemView.setOnClickListener { onItemClick(place) }
        }
    }

    class PlaceDiffCallback : DiffUtil.ItemCallback<PlaceEntity>() {
        override fun areItemsTheSame(oldItem: PlaceEntity, newItem: PlaceEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaceEntity, newItem: PlaceEntity): Boolean {
            return oldItem == newItem
        }
    }
}
