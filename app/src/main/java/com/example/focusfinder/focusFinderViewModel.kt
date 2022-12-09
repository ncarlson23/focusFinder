package com.example.focusfinder

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
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

    fun getTaskListFromDB() {
        taskList.value = taskDatabase.value?.TaskDAO()?.getTaskListFromDB()
        Log.d("TASK VALUE", taskList.value.toString())
        if (taskList.value == null) {
            taskList.value = emptyArray()
        }
    }

    fun addNewTask(type :Task){
        taskDatabase.value?.TaskDAO()?.insert(type)

    }

    fun updateTask(task:Task) {
        taskDatabase.value?.TaskDAO()?.updateTask(task.taskItem, task.date, task.priority, task.note, task.taskID)
    }

    fun deleteTask(taskPosition: Int) {
        val task = taskList.value?.get(taskPosition)
//        if(task !=null) {
//            taskDatabase.value?.

    }

    fun addNewMedicine(type: Medicine) {
    }


    fun deleteMedicine(type: Medicine) {
    }



}