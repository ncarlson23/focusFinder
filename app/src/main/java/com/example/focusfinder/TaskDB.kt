package com.example.focusfinder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Task::class], version = 1)
abstract class TaskDB:RoomDatabase() {
    abstract fun TaskDAO() : TaskDAO

    companion object {
        private var INSTANT: TaskDB? = null

        fun getDBObject(context: Context): TaskDB? {
            if (INSTANT == null) {
                synchronized(TaskDB::class.java) {
                    INSTANT = Room.databaseBuilder(context, TaskDB::class.java, "taskDB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANT
        }
    }
}