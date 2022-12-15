package com.example.focusfinder

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Medicine.kt
 * creates the medicine schema (object)
 * medicineID, overCounterName, officialName,
 * food, dosage, morning, afternoon, evening, notes
 */

@Entity(tableName = "medicineDB")
class Medicine {
    @PrimaryKey
    var medicineID: String = UUID.randomUUID().toString()
    var overCounterName = ""
    var officialName = ""
    var food = ""
    var dosage = ""
    var morning = false
    var afternoon = false
    var evening = false
    var notes = ""
}