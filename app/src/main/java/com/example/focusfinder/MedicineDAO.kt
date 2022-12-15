package com.example.focusfinder

/**
 * MedicineDAO.kt
 * DAO file for the medicine database
 */

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
    fun getMedicineFromDB(): Array<Medicine>

    // delete medicine item based on ID
    @Query("DELETE FROM medicineDB WHERE medicineID=:medID")
    fun deleteMedicineFromDB(medID: String)

    // update a medicine entry
    @Query("UPDATE medicineDB SET overCounterName=:overCounterName, officialName=:officialName, food=:food,dosage=:dosage, morning=:morning, afternoon=:afternoon,evening=:evening, notes=:notes WHERE medicineID=:medicineID")
    fun updateMedicine(
        medicineID: String,
        overCounterName: String,
        officialName: String,
        food: String,
        dosage: String,
        morning: Boolean,
        afternoon: Boolean,
        evening: Boolean,
        notes: String

    )


}