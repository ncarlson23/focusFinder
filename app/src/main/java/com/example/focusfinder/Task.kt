package com.example.focusfinder

/**
 * Task.kt
 * task schema (object)
 * taskID, taskItem, date, time, priority, note, checked
 */

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "taskDB")
class Task {
    @PrimaryKey
    var taskID: String = UUID.randomUUID().toString()
    var taskItem = ""
    var date = ""
    var time = ""
    var priority = 0
    var note = ""
    var checked = false

}