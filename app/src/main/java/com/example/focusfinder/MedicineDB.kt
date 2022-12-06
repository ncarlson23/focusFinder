package com.example.focusfinder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Medicine::class], version = 1)
abstract class MedicineDB:RoomDatabase() {
    abstract fun medicineDAO() : MedicineDAO

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

