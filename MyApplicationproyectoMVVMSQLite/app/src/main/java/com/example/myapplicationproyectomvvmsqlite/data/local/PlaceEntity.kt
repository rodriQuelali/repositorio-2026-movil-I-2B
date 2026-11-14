package com.example.myapplicationproyectomvvmsqlite.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val photoPath: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val createaAt: Long = System.currentTimeMillis()
)