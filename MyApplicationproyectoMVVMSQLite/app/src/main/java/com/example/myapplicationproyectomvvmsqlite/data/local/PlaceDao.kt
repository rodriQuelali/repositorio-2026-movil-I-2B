package com.example.myapplicationproyectomvvmsqlite.data.local

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {

    //las ocnfiguracione de mi api, mas adelante
    //SOlo de SQLite

    @Query("SELECT * FROM places ORDER BY createaAt DESC")
    fun getAll(): Flow<List<PlaceEntity>>




}