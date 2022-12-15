package com.example.focusfinder

/**
 * taskHome.kt
 * home page for task tracker
 * contains a vertical scrollable list of tasks
 * user can add, edit, delete tasks from list
 * tasks are sorted by completion and priority
 */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class taskHome : Fragment() {

    // initialize lateinit vars
    lateinit var task_home_home_button: Button
    lateinit var task_home_recycler_view: RecyclerView
    lateinit var task_home_add_button: Button


    // view manager and view adapter
    lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter

    // instance of viewmodel
    val viewModel: focusFinderViewModel by activityViewModels()


    // autogenerated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // first load most recent list of tasks
        viewModel.getTaskListFromDB();

        // connect lateinit vars to xml
        task_home_home_button = view.findViewById(R.id.task_home_home_button)
        task_home_recycler_view = view.findViewById(R.id.task_home_recycler_view)
        task_home_add_button = view.findViewById(R.id.task_home_add_button)


        // task click lambda so when task is clicked user can edit
        val taskClickLambda: (Task) -> Unit = {
            viewModel.currentTask.value = it
            findNavController().navigate(R.id.action_taskHome_to_taskDetail)
        }

        // taskdeletelambda so user can delete task
        val taskDeleteLambda: (Int) -> Unit = {
            viewModel.deleteTask(it)
        }


        // touch helper for swiping to delete task
        val touchHelper = TouchHelper(taskDeleteLambda)
        ItemTouchHelper(touchHelper).attachToRecyclerView(task_home_recycler_view)


        // recycler view setup
        viewManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewAdapter = viewModel.taskList.value?.let {
            RecyclerViewAdapter(
                it,
                viewModel
            )
        }!!  //having an issue
        viewAdapter.clickLambda = taskClickLambda
        task_home_recycler_view.layoutManager = viewManager
        task_home_recycler_view.adapter = viewAdapter

        viewModel.taskList.observe(viewLifecycleOwner, {
            viewAdapter.taskData = it
            viewAdapter.notifyDataSetChanged()
        })

        // nav buttons
        task_home_home_button.setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboard)
        }

        task_home_add_button.setOnClickListener {
            viewModel.currentTask.value = null // so that when you add a new task the form is blank
            findNavController().navigate(R.id.action_taskHome_to_taskDetail)
        }

    }


    // autogenerated
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_home, container, false)
    }

}