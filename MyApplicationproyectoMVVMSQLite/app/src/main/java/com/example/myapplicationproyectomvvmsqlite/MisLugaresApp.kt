package com.example.myapplicationproyectomvvmsqlite

import android.app.Application
import com.example.myapplicationproyectomvvmsqlite.data.local.AppDatabase
import com.example.myapplicationproyectomvvmsqlite.data.repository.PlaceRepository

class MisLugaresApp : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { PlaceRepository(database.placeDao()) }
}
