package com.example.focusfinder

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

    override fun getItemCount(): Int {
        return taskData.size
    }

    inner class RecyclerViewHolder(
        itemView: View, val clickLambda: (Task) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task, clickLambda: (Task) -> Unit) {

            itemView.findViewById<CheckBox>(R.id.task_item_checkbox).isChecked = task.checked
            itemView.findViewById<TextView>(R.id.task_item_name).text = task.taskItem

            var p = ""
            if (task.priority == 1) p = "!"
            if (task.priority == 2) p = "!!"
            if (task.priority == 3) p = "!!!"


            itemView.findViewById<TextView>(R.id.task_item_priority).text = p
            itemView.findViewById<TextView>(R.id.task_item_date).text = task.date + "@ " + task.time
            itemView.findViewById<TextView>(R.id.task_item_notes).text = task.note

            itemView.findViewById<Button>(R.id.task_item_edit_button).setOnClickListener {
                clickLambda(task)
            }

            var checkbox = itemView.findViewById<CheckBox>(R.id.task_item_checkbox)

            itemView.findViewById<CheckBox>(R.id.task_item_checkbox).setOnClickListener {
                if (checkbox.isChecked) {
                    task.checked = true
                    taskViewModel.updateTask(task)
                } else {
                    task.checked = false
                    taskViewModel.updateTask(task)
                }
                taskViewModel.getTaskListFromDB()

                taskViewModel.taskList.value!!.sortByDescending {
                    it.priority
                }

                taskViewModel.taskList.value!!.sortBy {
                    it.checked
                }

                taskViewModel.taskList.postValue(taskViewModel.taskList.value)
            }

            itemView.setOnClickListener {
                clickLambda(task)
            }


        }
    }
}