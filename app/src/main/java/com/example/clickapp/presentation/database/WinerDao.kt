package com.example.clickapp.presentation.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WinerDao {

    @Query("SELECT * FROM winerentity")
    fun getAll(): List<WinerEntity>

    @Query("SELECT * FROM winerentity WHERE id = :id")
    fun getBuId(id: Int): WinerEntity

    @Insert
    fun insert(winer: WinerEntity)

    @Update
    fun update(winer: WinerEntity)

    @Delete
    fun delete(winer: WinerEntity)
}