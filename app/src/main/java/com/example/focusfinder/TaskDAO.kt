package com.example.focusfinder

import androidx.room.*

@Dao
interface TaskDAO {
    // add a single task
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task:Task)

    // return list of all tasks
    @Query ("SELECT * FROM taskDB")
    fun getTaskListFromDB():Array<Task>

    // delete task item based on ID
    @Query("DELETE FROM taskDB WHERE taskID =:tID")
    fun deleteTaskFromDB(tID: String)

}