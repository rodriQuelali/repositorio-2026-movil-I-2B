package com.example.myapplicationproyectomvvmsqlite.ui.Place.form

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.myapplicationproyectomvvmsqlite.MisLugaresApp
import com.example.myapplicationproyectomvvmsqlite.R
import com.example.myapplicationproyectomvvmsqlite.ui.Place.PlaceViewModel
import com.example.myapplicationproyectomvvmsqlite.ui.Place.PlaceViewModelFactory
import com.example.myapplicationproyectomvvmsqlite.utils.FileUtils
import com.example.myapplicationproyectomvvmsqlite.utils.PermissionUtils
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.io.File

class PlaceFormFragment : Fragment() {

    private val viewModel: PlaceViewModel by viewModels {
        PlaceViewModelFactory((requireActivity().application as MisLugaresApp).repository)
    }

    private var currentPhotoPath: String? = null
    private var tempPhotoUri: Uri? = null

    private lateinit var etTitle: TextInputEditText
    private lateinit var etDescription: TextInputEditText
    private lateinit var tilTitle: TextInputLayout
    private lateinit var ivPhotoPreview: ImageView
    private lateinit var tvLocationStatus: TextView

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && currentPhotoPath != null) {
            viewModel.setPhoto(currentPhotoPath)
        }
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val localPath = FileUtils.copyUriToAppFile(requireContext(), uri)
            if (localPath != null) {
                viewModel.setPhoto(localPath)
            } else {
                Toast.makeText(requireContext(), "Error al cargar imagen de la galería", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            takePhoto()
        } else {
            Toast.makeText(requireContext(), "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (isGranted) {
            fetchLocation()
        } else {
            Toast.makeText(requireContext(), "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etTitle = view.findViewById(R.id.etTitle)
        etDescription = view.findViewById(R.id.etDescription)
        tilTitle = view.findViewById(R.id.tilTitle)
        ivPhotoPreview = view.findViewById(R.id.ivPhotoPreview)
        tvLocationStatus = view.findViewById(R.id.tvLocationStatus)

        val btnTakePhoto = view.findViewById<MaterialButton>(R.id.btnTakePhoto)
        val btnSelectGallery = view.findViewById<MaterialButton>(R.id.btnSelectGallery)
        val btnGetLocation = view.findViewById<MaterialButton>(R.id.btnGetLocation)
        val btnSave = view.findViewById<MaterialButton>(R.id.btnSave)

        btnTakePhoto.setOnClickListener {
            checkCameraPermissionAndTakePhoto()
        }

        btnSelectGallery.setOnClickListener {
            openGallery()
        }

        ivPhotoPreview.setOnClickListener {
            showImageSourceDialog()
        }

        btnGetLocation.setOnClickListener {
            if (PermissionUtils.hasLocationPermission(requireContext())) {
                fetchLocation()
            } else {
                locationPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }

        btnSave.setOnClickListener {
            val title = etTitle.text?.toString()?.trim().orEmpty()
            val description = etDescription.text?.toString()?.trim().orEmpty()

            if (title.isEmpty()) {
                tilTitle.error = getString(R.string.msg_title_required)
                return@setOnClickListener
            }
            tilTitle.error = null

            viewModel.save(title, description)
            Toast.makeText(requireContext(), getString(R.string.msg_place_saved), Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.draftPhoto.collect { photoPath ->
                        if (!photoPath.isNullOrEmpty()) {
                            val file = File(photoPath)
                            if (file.exists()) {
                                ivPhotoPreview.setImageURI(Uri.fromFile(file))
                            }
                        } else {
                            ivPhotoPreview.setImageResource(0)
                        }
                    }
                }
                launch {
                    viewModel.draftLocation.collect { location ->
                        if (location != null) {
                            tvLocationStatus.text = "Lat: %.4f, Lng: %.4f".format(location.first, location.second)
                        } else {
                            tvLocationStatus.text = getString(R.string.location_not_available)
                        }
                    }
                }
            }
        }
    }

    private fun showImageSourceDialog() {
        val options = arrayOf(
            getString(R.string.option_camera),
            getString(R.string.option_gallery)
        )
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.select_photo_title)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> checkCameraPermissionAndTakePhoto()
                    1 -> openGallery()
                }
            }
            .show()
    }

    private fun checkCameraPermissionAndTakePhoto() {
        if (PermissionUtils.hasCameraPermission(requireContext())) {
            takePhoto()
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun takePhoto() {
        try {
            val (uri, path) = FileUtils.createImageUri(requireContext())
            tempPhotoUri = uri
            currentPhotoPath = path
            cameraLauncher.launch(uri)
        } catch (_: Exception) {
            Toast.makeText(requireContext(), "Error al abrir la cámara", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchLocation() {
        try {
            val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val lastGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val lastNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            val lastPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)

            val location: Location? = lastGps ?: lastNetwork ?: lastPassive

            if (location != null) {
                viewModel.setLocation(location.latitude, location.longitude)
                Toast.makeText(requireContext(), "Ubicación obtenida", Toast.LENGTH_SHORT).show()
            } else {
                val provider = when {
                    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> LocationManager.GPS_PROVIDER
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> LocationManager.NETWORK_PROVIDER
                    else -> null
                }

                if (provider != null) {
                    Toast.makeText(requireContext(), "Obteniendo ubicación...", Toast.LENGTH_SHORT).show()
                    val locationListener = object : LocationListener {
                        override fun onLocationChanged(loc: Location) {
                            viewModel.setLocation(loc.latitude, loc.longitude)
                            Toast.makeText(requireContext(), "Ubicación actualizada", Toast.LENGTH_SHORT).show()
                            locationManager.removeUpdates(this)
                        }
                        @Deprecated("Deprecated in Java")
                        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
                        override fun onProviderEnabled(p0: String) {}
                        override fun onProviderDisabled(p0: String) {}
                    }
                    locationManager.requestLocationUpdates(provider, 1000L, 0f, locationListener)
                } else {
                    Toast.makeText(requireContext(), "Por favor activa el GPS en la configuración del dispositivo", Toast.LENGTH_LONG).show()
                }
            }
        } catch (_: SecurityException) {
            Toast.makeText(requireContext(), "Sin permiso de ubicación", Toast.LENGTH_SHORT).show()
        } catch (_: Exception) {
            Toast.makeText(requireContext(), "Error al obtener ubicación", Toast.LENGTH_SHORT).show()
        }
    }
}
