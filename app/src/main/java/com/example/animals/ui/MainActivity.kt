package com.example.animals.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animals.R
import com.example.animals.databinding.ActivityMainBinding
import com.example.animals.databinding.DialogAddAnimalBinding
import com.example.animals.databinding.DialogEditAnimalBinding
import com.example.animals.databinding.DialogFilterBinding
import com.example.animals.db.Animal
//import com.example.animals.db.SQLIteDBHelper
import com.example.animals.db.room.ViewModel
import kotlinx.android.synthetic.main.dialog_add_animal.view.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainActivity : AppCompatActivity(), AnimalsListener {

    private lateinit var viewModel: ViewModel
    private val animalsAdapter: AnimalsAdapter = AnimalsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = animalsAdapter
        }
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.readAllAnimalByOrder.observe(this, { animal -> animalsAdapter.setData(animal) })
        binding.buttonAdd.setOnClickListener { showAddAnimalDialog(this) }
        binding.buttonFilter.setOnClickListener { showFilterDialog(this) }
        binding.buttonSettings.setOnClickListener {
            Toast.makeText(this, "I could not make the cursor implementation.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showEditDialog(context: Context, animal: Animal) {
        val dialog = Dialog(context)
        val dialogBinding = DialogEditAnimalBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)


        dialogBinding.fieldAnimalName.setText(animal.name)
        dialogBinding.fieldAnimalAge.setText(animal.age.toString())
        dialogBinding.fieldAnimalBreed.setText(animal.breed)
        val languages = resources.getStringArray(R.array.breeds)
        val arrayAdapter = ArrayAdapter(context, R.layout.item_dropdown, languages)
        dialogBinding.textInputBreed.field_animal_breed.setAdapter(arrayAdapter)

        initEditDialogButtons(dialogBinding, dialog, animal)

        showDialog(dialog)
    }

    private fun showFilterDialog(context: Context) {
        val dialog = Dialog(context)
        val dialogBinding = DialogFilterBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        initFilterDialogButtons(dialogBinding, dialog)

        showDialog(dialog)
    }

    private fun showAddAnimalDialog(context: Context) {
        val dialog = Dialog(context)
        val dialogBinding = DialogAddAnimalBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        val languages = resources.getStringArray(R.array.breeds)
        val arrayAdapter = ArrayAdapter(context, R.layout.item_dropdown, languages)
        dialogBinding.textInputBreed.field_animal_breed.setAdapter(arrayAdapter)

        initAddDialogButtons(dialogBinding, dialog)

        showDialog(dialog)
    }

    private fun initFilterDialogButtons(binding: DialogFilterBinding, dialog: Dialog) {
        binding.buttonSortByName.setOnClickListener {
            viewModel.sortBy("name")
            dialog.dismiss()
        }
        binding.buttonSortByBreed.setOnClickListener {
            viewModel.sortBy("breed")
            dialog.dismiss()
        }
        binding.buttonSortByAge.setOnClickListener {
            viewModel.sortBy("age")
            dialog.dismiss()
        }
    }

    private fun initEditDialogButtons(binding: DialogEditAnimalBinding, dialog: Dialog, animal: Animal) {
        binding.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.buttonEnter.setOnClickListener {
            val newAnimal = Animal(
                animal.id,
                binding.fieldAnimalName.text.toString(),
                binding.fieldAnimalAge.text.toString().toInt(),
                binding.fieldAnimalBreed.text.toString()
            )
            viewModel.updateData(newAnimal)
            dialog.dismiss()
        }
    }

    private fun initAddDialogButtons(binding: DialogAddAnimalBinding, dialog: Dialog) {
        binding.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.buttonEnter.setOnClickListener {
            addNewAnimal(
                0,
                binding.fieldAnimalName.text.toString(),
                binding.fieldAnimalAge.text.toString().toInt(),
                binding.fieldAnimalBreed.text.toString()
            )
            dialog.dismiss()
        }
    }

    private fun showDialog(dialog: Dialog) {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window!!.attributes)
        val color = ColorDrawable(Color.TRANSPARENT)
        dialog.window!!.setBackgroundDrawable(color)
        dialog.show()
        dialog.window!!.attributes = layoutParams
    }

    private fun addNewAnimal(id: Int = 0, name: String = "", age: Int = 0, breed: String = "") {
        val animal = Animal(
            id,
            name,
            age,
            breed
        )
        viewModel.addData(animal)
    }

    override fun deleteAnimal(id: Int) {
        viewModel.deleteDataById(id)
    }

    override fun editAnimal(animal: Animal) {
        showEditDialog(this, animal)
    }
}