package com.example.focusfinder

/**
 * RecyclerViewAdapter.kt
 * recycler view adapter for the task data
 * update the recycler view when tasks are added, updated, deleted
 *
 * This recyclerViewAdapter is for TASK DATA!
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// this is recycler for task list

class RecyclerViewAdapter(var taskData: Array<Task>, val taskViewModel: focusFinderViewModel) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {


    // click lambda
    lateinit var clickLambda: (Task) -> Unit


    // auto generated
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return RecyclerViewHolder(viewItem, clickLambda)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // set the data in terms of the recyclerView
        holder.bind(taskData[position], clickLambda)

    }

    // autogenerated
    override fun getItemCount(): Int {
        return taskData.size
    }

    inner class RecyclerViewHolder(
        itemView: View, val clickLambda: (Task) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task, clickLambda: (Task) -> Unit) {

            // get data for name and checkbox
            itemView.findViewById<CheckBox>(R.id.task_item_checkbox).isChecked = task.checked
            itemView.findViewById<TextView>(R.id.task_item_name).text = task.taskItem


            // get values for priority ranking and set them to appropriate number of !s
            var p = ""
            if (task.priority == 1) p = "!"
            if (task.priority == 2) p = "!!"
            if (task.priority == 3) p = "!!!"


            itemView.findViewById<TextView>(R.id.task_item_priority).text = p

            // format display for task time and date
            if (task.time == "") {
                if (task.date == "") {
                    itemView.findViewById<TextView>(R.id.task_item_date).text = ""
                } else {
                    itemView.findViewById<TextView>(R.id.task_item_date).text = task.date
                }
            } else {
                itemView.findViewById<TextView>(R.id.task_item_date).text =
                    task.date + " @ " + task.time
            }




            itemView.findViewById<TextView>(R.id.task_item_notes).text = task.note

            //set on click listener for edit task, takes user detail screen
            itemView.findViewById<Button>(R.id.task_item_edit_button).setOnClickListener {
                clickLambda(task)
            }

            var checkbox = itemView.findViewById<CheckBox>(R.id.task_item_checkbox)

            // if checkbox is checked it means task has been completed
            // move task to the bottom of the list
            itemView.findViewById<CheckBox>(R.id.task_item_checkbox).setOnClickListener {
                if (checkbox.isChecked) {
                    task.checked = true
                    taskViewModel.updateTask(task)
                } else {
                    task.checked = false
                    taskViewModel.updateTask(task)
                }
                taskViewModel.getTaskListFromDB()


                taskViewModel.taskList.postValue(taskViewModel.taskList.value)
            }

            itemView.setOnClickListener {
                clickLambda(task)
            }


        }
    }
}