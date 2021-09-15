package com.example.animals.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animals.db.Animal
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Animal::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: com.example.animals.db.room.Database? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): com.example.animals.db.room.Database {
            var instance = INSTANCE
            if (instance != null) {
                return instance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.animals.db.room.Database::class.java,
                    "ANIMALS"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}