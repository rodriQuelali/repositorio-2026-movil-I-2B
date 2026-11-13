package com.example.myapplicationproyectomvvmsqlite.data.repository

import com.example.myapplicationproyectomvvmsqlite.data.local.PlaceDao
import com.example.myapplicationproyectomvvmsqlite.data.local.PlaceEntity
import kotlinx.coroutines.flow.Flow

class PlaceRepository(private val dao: PlaceDao) {

    val places: Flow<List<PlaceEntity>> = dao.getAll()

    suspend fun getById(id: Int) = dao.getById(id)
    suspend fun add(place: PlaceEntity) = dao.insert(place)
    suspend fun update(place: PlaceEntity) = dao.update(place)
    suspend fun delete(place: PlaceEntity) = dao.delete(place)
}