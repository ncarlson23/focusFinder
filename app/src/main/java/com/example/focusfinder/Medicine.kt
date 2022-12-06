package com.example.focusfinder

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="medicineDB")
class Medicine {
    @PrimaryKey

    var overCounterName = ""
    var officialName = ""
    var food = ""
    var dosage = ""
    var morning = false
    var afternoon = false
    var evening = false
    var notes = ""
}