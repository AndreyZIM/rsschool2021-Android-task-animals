package com.example.animals.db.room

import android.app.Application
import androidx.lifecycle.*
import com.example.animals.db.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ViewModel(application: Application): AndroidViewModel(application) {

    private var sort: MutableLiveData<String> = MutableLiveData(NAME_SORT)
    private val repository: Repository
    init {
        val dao = Database.getDatabase(application).dao()
        repository = Repository(dao)
    }
    var readAllAnimalByOrder: LiveData<List<Animal>> = Transformations.switchMap(sort) {
        order -> repository.readAllAnimalByOrder(order)
    }

    fun addData(animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(animal)
        }
    }

    fun updateData(animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(animal)
        }
    }

    fun deleteData(animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(animal)
        }
    }

    fun deleteDataById(animalId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDataById(animalId)
        }
    }

    fun sortBy(order: String) {
        sort.value = order
    }

    companion object {
        const val NAME_SORT = "id"
    }
}