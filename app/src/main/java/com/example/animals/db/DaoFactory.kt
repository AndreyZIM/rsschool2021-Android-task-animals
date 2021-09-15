package com.example.animals.db

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.room.Room
//import com.example.animals.db.cursor.AnimalCursorDatabase
import com.example.animals.db.room.Dao
import com.example.animals.db.room.Database

//class DaoFactory(val context: Context) {
//
//    private val sharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }
//    private val database: Database =
//        Room.databaseBuilder(
//            context.applicationContext,
//            Database::class.java,
//            "ANIMALS"
//        ).build()
//    private val roomImplementation by lazy { database.dao() }
//    private val cursorImplementation by lazy { AnimalCursorDatabase(context)}
//
//    var dao: Dao = database.dao()
//        get() = if (sharedPreferences.getBoolean(SWITCH, true)) roomImplementation else cursorImplementation
//
//    companion object {
//        private const val SWITCH = "switch"
//    }
//}