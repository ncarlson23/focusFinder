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
    fun getMedicineFromDB():Array<Medicine>

    // delete medicine item based on ID
    @Query("DELETE FROM medicineDB WHERE medicineID=:medID")
    fun deleteMedicineFromDB(medID: String)



}