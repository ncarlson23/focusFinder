package com.example.focusfinder

/**
 * TaskDB.kt
 * creates the room database to keep track of task entries from user
 * stored locally on android device
 * template code provided by Professor Shuo Niu, PhD.
 */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 5)
abstract class TaskDB : RoomDatabase() {
    abstract fun TaskDAO(): TaskDAO

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