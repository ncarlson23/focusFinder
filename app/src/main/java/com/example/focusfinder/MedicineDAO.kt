package com.example.focusfinder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MedicineDAO {

    // add a single task
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(medicine: Medicine)

    // return list of all tasks
    @Query("SELECT * FROM medicineDB")
    fun getTaskMedicineFromDB():List<Medicine>

}