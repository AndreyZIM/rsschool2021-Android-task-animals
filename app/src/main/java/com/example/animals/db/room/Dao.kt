package com.example.animals.db.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.animals.db.Animal

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(animal: Animal)

    @Query("SELECT * FROM animals ORDER BY " +
            "CASE WHEN :order = 'id' THEN id END," +
            "CASE WHEN :order = 'name' THEN name END," +
            "CASE WHEN :order = 'age' THEN age END," +
            "CASE WHEN :order = 'breed' THEN breed END")
    fun readAllAnimalsByOrder(order: String): LiveData<List<Animal>>

    @Update
    suspend fun updateData(animal: Animal)

    @Delete
    suspend fun deleteData(animal: Animal)

    @Query("DELETE FROM animals WHERE id = :animalId")
    suspend fun deleteDataById(animalId: Int)
}