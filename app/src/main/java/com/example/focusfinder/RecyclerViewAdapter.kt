package com.example.focusfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

// this is recycler for task list

class RecyclerViewAdapter(var taskData:Array<Task>, val taskViewModel: focusFinderViewModel) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

//    val viewModel : focusFinderViewModel by viewModel()

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
        itemView: View, val clickLambda: (Task) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task, clickLambda: (Task) -> Unit) {

            itemView.findViewById<CheckBox>(R.id.task_item_checkbox).isChecked = false
            itemView.findViewById<TextView>(R.id.task_item_name).text = task.taskItem
            itemView.findViewById<TextView>(R.id.task_item_priority).text = "!"
            itemView.findViewById<TextView>(R.id.task_item_date).text = task.date
            itemView.findViewById<TextView>(R.id.task_item_notes).text = task.note

//            itemView.findViewById<Button>(R.id.task_item_edit_button).setOnClickListener {
//                taskViewModel.currentTask.value = task
//            }

            itemView.setOnClickListener {
                clickLambda(task)
            }


        }
    }
}