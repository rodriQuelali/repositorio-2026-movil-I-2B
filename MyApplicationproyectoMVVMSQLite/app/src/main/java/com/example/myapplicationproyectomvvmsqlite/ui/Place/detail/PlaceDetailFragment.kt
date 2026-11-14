package com.example.myapplicationproyectomvvmsqlite.ui.Place.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplicationproyectomvvmsqlite.MisLugaresApp
import com.example.myapplicationproyectomvvmsqlite.R
import com.example.myapplicationproyectomvvmsqlite.data.local.PlaceEntity
import com.example.myapplicationproyectomvvmsqlite.ui.Place.PlaceViewModel
import com.example.myapplicationproyectomvvmsqlite.ui.Place.PlaceViewModelFactory
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PlaceDetailFragment : Fragment() {

    private val viewModel: PlaceViewModel by viewModels {
        PlaceViewModelFactory((requireActivity().application as MisLugaresApp).repository)
    }

    private var currentPlace: PlaceEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val placeId = arguments?.getInt("placeId", -1) ?: -1
        if (placeId == -1) {
            findNavController().navigateUp()
            return
        }

        val ivPhoto = view.findViewById<ImageView>(R.id.ivDetailPhoto)
        val tvTitle = view.findViewById<TextView>(R.id.tvDetailTitle)
        val tvDate = view.findViewById<TextView>(R.id.tvDetailDate)
        val tvDescription = view.findViewById<TextView>(R.id.tvDetailDescription)
        val tvLocation = view.findViewById<TextView>(R.id.tvDetailLocation)
        val btnDelete = view.findViewById<MaterialButton>(R.id.btnDeletePlace)

        btnDelete.setOnClickListener {
            currentPlace?.let { place ->
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.confirm_delete_title)
                    .setMessage(R.string.confirm_delete_msg)
                    .setPositiveButton(R.string.yes) { _, _ ->
                        viewModel.delete(place)
                        Toast.makeText(requireContext(), getString(R.string.msg_place_deleted), Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                    .setNegativeButton(R.string.no, null)
                    .show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val place = viewModel.getById(placeId)
            if (place != null) {
                currentPlace = place
                tvTitle.text = place.title
                tvDescription.text = place.description

                val formattedDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(place.createaAt))
                tvDate.text = "Fecha: $formattedDate"

                if (place.latitude != null && place.longitude != null) {
                    tvLocation.visibility = View.VISIBLE
                    tvLocation.text = "Ubicación: Lat %.4f, Lng %.4f".format(place.latitude, place.longitude)
                } else {
                    tvLocation.visibility = View.GONE
                }

                if (!place.photoPath.isNullOrEmpty()) {
                    val file = File(place.photoPath)
                    if (file.exists()) {
                        ivPhoto.setImageURI(Uri.fromFile(file))
                    } else {
                        ivPhoto.setImageResource(R.drawable.ic_launcher_background)
                    }
                } else {
                    ivPhoto.setImageResource(R.drawable.ic_launcher_background)
                }
            } else {
                findNavController().navigateUp()
            }
        }
    }
}
