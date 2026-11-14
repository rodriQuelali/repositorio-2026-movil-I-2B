package com.example.myapplicationproyectomvvmsqlite.ui.Place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplicationproyectomvvmsqlite.data.local.PlaceEntity
import com.example.myapplicationproyectomvvmsqlite.data.repository.PlaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {

    // Lista para el RecyclerView
    val places: StateFlow<List<PlaceEntity>> = repository.places
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    // Borrador del formulario
    private val _draftPhoto = MutableStateFlow<String?>(null)
    val draftPhoto: StateFlow<String?> = _draftPhoto

    private val _draftLocation = MutableStateFlow<Pair<Double, Double>?>(null)
    val draftLocation: StateFlow<Pair<Double, Double>?> = _draftLocation

    fun setPhoto(path: String?) { _draftPhoto.value = path }
    fun setLocation(lat: Double, lng: Double) { _draftLocation.value = lat to lng }

    fun clearDraft() {
        _draftPhoto.value = null
        _draftLocation.value = null
    }

    fun save(title: String, description: String) = viewModelScope.launch {
        repository.add(
            PlaceEntity(
                title = title,
                description = description,
                photoPath = _draftPhoto.value,
                latitude = _draftLocation.value?.first,
                longitude = _draftLocation.value?.second
            )
        )
        clearDraft()
    }

    suspend fun getById(id: Int): PlaceEntity? = repository.getById(id)

    fun delete(place: PlaceEntity) = viewModelScope.launch { repository.delete(place) }
}

class PlaceViewModelFactory(private val repository: PlaceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlaceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
