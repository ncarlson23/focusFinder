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

    // task database
    val taskDatabase = MutableLiveData<TaskDB>()

    // medicine database
    val medicineDatabase = MutableLiveData<MedicineDB>()


    init {
        medicineList.value = emptyArray()
        taskList.value = emptyArray()
        currentMedicine.value = null
        currentTask.value = null
    }

    fun getMedicineListFromDB()  {
        medicineList.value = medicineDatabase.value?.medicineDAO()?.getMedicineFromDB()
    }

    fun getTaskListFromDB(type:Array<Task>) {
        taskList.value = taskDatabase.value?.TaskDAO()?.getTaskListFromDB()
    }

    fun addNewTask(type :Task){

    }

    fun addNewMedicine(type: Medicine) {
    }

    fun deleteTask(type: Task){
    }

    fun deleteMedicine(type: Medicine) {
    }



}