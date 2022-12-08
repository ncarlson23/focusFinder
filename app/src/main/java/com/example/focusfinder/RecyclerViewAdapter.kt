package com.example.focusfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// this is recycler for task list

class RecyclerViewAdapter(var taskData:Array<Task>, val taskViewModel: focusFinderViewModel) :
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
        itemView: View, val clickLambda: (Task) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Task, clickLambda: (Task) -> Unit) {

            itemView.setOnClickListener {
                clickLambda(movie)
            }


        }
    }
}