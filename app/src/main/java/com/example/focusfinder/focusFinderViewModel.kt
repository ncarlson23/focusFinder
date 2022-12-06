package com.example.focusfinder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class focusFinderViewModel: ViewModel() {

    // list of medicines
    val medicineList = MutableLiveData<Array<Medicine>>()

    // list of tasks
    val taskList = MutableLiveData<Array<Task>>()

    // current medicine selected
    val currentMedicine = MutableLiveData<Medicine?>()

    // current task selected
    val currentTask = MutableLiveData<Task?>()

    init {
        medicineList.value = emptyArray()
        taskList.value = emptyArray()
        currentMedicine.value = null
        currentTask.value = null
    }

//    fun getMedicineListFromDB(): Array<Medicine> {
//
//    }

//    fun getTaskListFromDB(): Array<Task> {
//    }

    fun addNewTask(type :Task){
    }

    fun addNewMedicine(type: Medicine) {
    }

    fun deleteTask(type: Task){
    }

    fun deleteMedicine(type: Medicine) {
    }



}