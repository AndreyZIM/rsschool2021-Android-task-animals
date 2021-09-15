package com.example.animals.db.cursor

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.animals.db.Animal
import com.example.animals.db.room.Dao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//class AnimalCursorDatabase(context: Context) :
//    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION),
//    Dao {
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        try {
//            db?.execSQL(CREATE_TABLE_SQL)
//        } catch (exception: SQLException) {
//            Log.e(LOG_TAG, SQLITE_ERR, exception)
//        }
//    }
//
//    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
//    }
//
//    override suspend fun addData(animal: Animal) {
//        Log.d(LOG_TAG_SQL, "SQL addData initiated")
//        println("addAnimal Cursor")
//        val db = writableDatabase
//        val values = ContentValues()
//        values.put(NAME_FIELD, animal.name)
//        values.put(BREED_FIELD, animal.breed)
//        values.put(AGE_FIELD, animal.age)
//        db.insert(TABLE_NAME, null, values)
//        db.close()
//    }
//
////    override fun readAllAnimal(): LiveData<List<Animal>> {
////        TODO("Not yet implemented")
////    }
//
//    @SuppressLint("Range")
//    private suspend fun getAnimalList(orderList: String): List<Animal> {
//        return withContext(Dispatchers.IO) {
//            val listOfCars = mutableListOf<Animal>()
//            val db = writableDatabase
//            val selectQuery = "SELECT * FROM ${TABLE_NAME} ORDER BY $orderList"
//            val cursor = db.rawQuery(selectQuery, null)
//            cursor?.let {
//                if (cursor.moveToFirst()) {
//                    do {
//                        val id = cursor.getInt(cursor.getColumnIndex(ID))
//                        val name = cursor.getString(cursor.getColumnIndex(NAME_FIELD))
//                        val breed = cursor.getString(cursor.getColumnIndex(BREED_FIELD))
//                        val age = cursor.getInt(cursor.getColumnIndex(AGE_FIELD))
//                        listOfCars.add(Animal(id, name, age, breed))
//                    } while (cursor.moveToNext())
//                }
//            }
//            cursor.close()
//            listOfCars
//        }
//    }
//
//    override fun readAllAnimalsByOrder(order: String): LiveData<List<Animal>> {
//        return liveData<List<Animal>> {
//            emit(getAnimalList(order))
//        }
//    }
//
//    override suspend fun updateData(animal: Animal) {
//        println("update Cursor")
//        val db = writableDatabase
//        val values = ContentValues()
//        values.put(ID, animal.id)
//        values.put(NAME_FIELD, animal.name)
//        values.put(BREED_FIELD, animal.breed)
//        values.put(AGE_FIELD, animal.age)
//        db.update(TABLE_NAME, values, "${ID}=?", arrayOf(animal.id.toString()))
//        db.close()
//    }
//
//    override suspend fun deleteData(animal: Animal) {
//        val db = writableDatabase
//        db.delete(TABLE_NAME, "${ID}=?", arrayOf(animal.id.toString()))
//        db.close()
//    }
//
//    override suspend fun deleteDataById(animalId: Int) {
//        val db = writableDatabase
//        db.delete(TABLE_NAME, "${ID}=?", arrayOf(animalId.toString()))
//        db.close()
//    }
//
//    companion object {
//        const val DATABASE_NAME = "ANIMALS"
//        const val TABLE_NAME = "animals"
//        const val DATABASE_VERSION = 1
//        const val CREATE_TABLE_SQL =
//            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
//                    "id	INTEGER NOT NULL," +
//                    "name	TEXT NOT NULL," +
//                    "breed	TEXT NOT NULL," +
//                    "age	INTEGER NOT NULL," +
//                    "PRIMARY KEY(id AUTOINCREMENT)" +
//                    ");"
//        const val NAME_FIELD = "name"
//        const val BREED_FIELD = "breed"
//        const val AGE_FIELD = "age"
//        const val ID = "id"
//        private const val LOG_TAG = "SQLiteOpenHelper"
//        private const val LOG_TAG_SQL = "DBase"
//        private const val SQLITE_ERR = "SQLiteOpenHelper ERROR:"
//    }
//}