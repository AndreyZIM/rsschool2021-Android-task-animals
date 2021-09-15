package com.example.animals.db.room

import androidx.lifecycle.LiveData
import com.example.animals.db.Animal
//import com.example.animals.db.DaoFactory

class Repository (private val dao: Dao) {

    suspend fun addData(animal: Animal) {
        dao.addData(animal)
    }

    fun readAllAnimalByOrder(order: String): LiveData<List<Animal>> =
        dao.readAllAnimalsByOrder(order)

    suspend fun updateData(animal: Animal) {
        dao.updateData(animal)
    }

    suspend fun deleteData(animal: Animal) {
        dao.deleteData(animal)
    }

    suspend fun deleteDataById(animalId: Int) {
        dao.deleteDataById(animalId)
    }
}