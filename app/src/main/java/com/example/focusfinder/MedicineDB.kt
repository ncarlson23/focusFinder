package com.example.focusfinder

/**
 * MedicineDB.kt
 * creates the room database to keep track of medicine entries from user
 * stored locally on android device
 * template code provided by Professor Shuo Niu, PhD.
 */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Medicine::class], version = 1)
abstract class MedicineDB : RoomDatabase() {
    abstract fun medicineDAO(): MedicineDAO

    companion object {
        private var INSTANT: MedicineDB? = null

        fun getDBObject(context: Context): MedicineDB? {
            if (INSTANT == null) {
                synchronized(MedicineDB::class.java) {
                    INSTANT = Room.databaseBuilder(context, MedicineDB::class.java, "medicineDB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANT
        }
    }
}

