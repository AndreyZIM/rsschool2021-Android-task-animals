package com.example.animals.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animals.databinding.ItemAnimalBinding
import com.example.animals.db.Animal

class AnimalsAdapter: RecyclerView.Adapter<AnimalsViewHolder>() {

    private var animals = emptyList<Animal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimalBinding.inflate(layoutInflater, parent, false)
        return AnimalsViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        holder.bind(animals[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(animals: List<Animal>) {
        this.animals = animals
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return animals.size
    }
}