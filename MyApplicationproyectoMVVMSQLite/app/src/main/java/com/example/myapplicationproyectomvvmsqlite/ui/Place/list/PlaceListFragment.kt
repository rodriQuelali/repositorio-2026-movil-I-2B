package com.example.myapplicationproyectomvvmsqlite.ui.Place.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationproyectomvvmsqlite.MisLugaresApp
import com.example.myapplicationproyectomvvmsqlite.R
import com.example.myapplicationproyectomvvmsqlite.ui.Place.PlaceViewModel
import com.example.myapplicationproyectomvvmsqlite.ui.Place.PlaceViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class PlaceListFragment : Fragment() {

    private val viewModel: PlaceViewModel by viewModels {
        PlaceViewModelFactory((requireActivity().application as MisLugaresApp).repository)
    }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvPlaces = view.findViewById<RecyclerView>(R.id.rvPlaces)
        val tvEmpty = view.findViewById<TextView>(R.id.tvEmptyPlaces)
        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAddPlace)

        adapter = PlaceAdapter { place ->
            val bundle = Bundle().apply { putInt("placeId", place.id) }
            findNavController().navigate(R.id.action_placeListFragment_to_placeDetailFragment, bundle)
        }
        rvPlaces.adapter = adapter

        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_placeListFragment_to_placeFormFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.places.collect { list ->
                    adapter.submitList(list)
                    if (list.isEmpty()) {
                        tvEmpty.visibility = View.VISIBLE
                        rvPlaces.visibility = View.GONE
                    } else {
                        tvEmpty.visibility = View.GONE
                        rvPlaces.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
