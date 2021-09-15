package com.example.animals.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.animals.R
import com.example.animals.databinding.ItemAnimalBinding
import com.example.animals.db.Animal

class AnimalsViewHolder(
    private val binding: ItemAnimalBinding,
    private val context: Context
) :
    RecyclerView.ViewHolder(binding.root) {

    private val listener: AnimalsListener? = if (context is AnimalsListener) context else null

    @SuppressLint("SetTextI18n")
    fun bind(animal: Animal) {
        binding.itemName.text = animal.name
        binding.itemDescription.text = "Breed: ${animal.breed}, Age: ${animal.age}"
        binding.itemIcon.setImageResource(
            when (animal.breed) {
                "Cat" -> R.drawable.cat
                "Dog" -> R.drawable.dog
                "Fox" -> R.drawable.fox
                "Sheep" -> R.drawable.sheep
                else -> R.drawable.cat
            }
        )
        binding.buttonDelete.setOnClickListener {
            deleteAnimal(animal)
        }
        binding.buttonEdit.setOnClickListener {
            editAnimal(animal)
        }
    }

    private fun deleteAnimal(animal: Animal) {
        listener?.deleteAnimal(animal.id)
    }

    private fun editAnimal(animal: Animal) {
        listener?.editAnimal(animal)
    }
}