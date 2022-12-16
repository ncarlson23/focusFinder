package com.example.focusfinder

/**
 * focusFinderViewModel.kt
 * view model for focus finder app
 * Contains several functions for manipulating data within task and medicine databases
 * corresponds with the TaskDAO and the MedicineDAO
 */

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class focusFinderViewModel : ViewModel() {

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

    // constructor
    init {

        medicineList.value = emptyArray()
        taskList.value = emptyArray()
        getMedicineListFromDB()
        getTaskListFromDB()
        currentMedicine.value = null
        currentTask.value = null
    }

    /**
     * fun getMedicineListFromDB()
     * gets the list of medicines from the medicine database
     * reverse order to put most recently added medicine at front of list l
     */
    fun getMedicineListFromDB() {
        medicineList.value = medicineDatabase.value?.medicineDAO()?.getMedicineFromDB()
        if (medicineList.value == null) {
            medicineList.value = emptyArray()
        }
        medicineList.value?.reverse()

    }

    /**
     * fun getTaskListFromDB()
     * gets the list of tasks from the task database
     * sort the tasks by priority descending order to put most important tasks at the top
     * sort the tasks by completion to put completed tasks at the bottom
     */
    fun getTaskListFromDB() {
        taskList.value = taskDatabase.value?.TaskDAO()?.getTaskListFromDB()
        if (taskList.value == null) {
            taskList.value = emptyArray()
        }
        taskList.value!!.sortByDescending {
            it.priority
        }

        taskList.value!!.sortBy {
            it.checked
        }
    }

    /**
     * fun addNewTask()
     * add new task to the task database
     */
    fun addNewTask(type: Task) {
        taskDatabase.value?.TaskDAO()?.insert(type)

    }

    /**
     * fun updateTask()
     * when the user edits a task, it updates in the view model
     * update all parameters of the task object
     */
    fun updateTask(task: Task) {
        taskDatabase.value?.TaskDAO()?.updateTask(
            task.taskItem,
            task.date,
            task.priority,
            task.note,
            task.taskID,
            task.checked,
            task.time
        )
    }

    /**
     * fun deleteTask(taskPosition:Int)
     * delete a task from the task database
     * post updated task list without the deleted task
     */
    fun deleteTask(taskPosition: Int) {
        val task = taskList.value?.get(taskPosition)
        if (task != null) {
            taskDatabase.value?.TaskDAO()?.deleteTaskFromDB(task.taskID)
        }
        getTaskListFromDB()
        taskList.postValue(taskList.value)
    }

    /**
     * fun addNewMedicine(type: Medicine)
     * add a medicine to the medicine database
     */
    fun addNewMedicine(type: Medicine) {
        medicineDatabase.value?.medicineDAO()?.insert(type)
    }


    /**
     * fun deleteMedicine(type: Medicine)
     * delete a medicine from the medicine database
     * post updated medicine list without the recently deleted medicine
     */
    fun deleteMedicine(type: Medicine) {
        medicineDatabase.value?.medicineDAO()?.deleteMedicineFromDB(type.medicineID)
        getMedicineListFromDB()
        medicineList.postValue(medicineList.value)
    }

    /**
     * fun updateMedicine(medicine:Medicine)
     * updates medicine when the user edits it
     * update all parameters of medicine object
     */
    fun updateMedicine(medicine: Medicine) {
        medicineDatabase.value?.medicineDAO()?.updateMedicine(
            medicine.medicineID,
            medicine.overCounterName,
            medicine.officialName,
            medicine.food,
            medicine.dosage,
            medicine.morning,
            medicine.afternoon,
            medicine.evening,
            medicine.notes
        )

    }


}