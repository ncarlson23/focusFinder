package com.example.focusfinder

import androidx.room.*

@Dao
interface TaskDAO {

    @Query ("SELECT * FROM taskDB")
    fun getTaskListFromDB():List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewTask(type:Task)

   // @Delete()

}