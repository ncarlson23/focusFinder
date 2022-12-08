package com.example.focusfinder


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "taskDB")
class Task {
    @PrimaryKey
    var taskID : String = UUID.randomUUID().toString()
    var taskItem = ""
    var date = ""
    var priority = 0
    var note = ""

}