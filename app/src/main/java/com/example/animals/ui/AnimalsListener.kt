package com.example.animals.ui

import com.example.animals.db.Animal

interface AnimalsListener {
    fun deleteAnimal(id: Int)
    fun editAnimal(animal: Animal)
}