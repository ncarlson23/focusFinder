package com.example.focusfinder

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

    lateinit var task_home_home_button: Button
    lateinit var task_home_recycler_view: RecyclerView
    lateinit var task_home_add_button: Button

    lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter

    val viewModel: focusFinderViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // first load most recent list of tasks
        viewModel.getTaskListFromDB();

        // do sorting here


        task_home_home_button = view.findViewById(R.id.task_home_home_button)
        task_home_recycler_view = view.findViewById(R.id.task_home_recycler_view)
        task_home_add_button = view.findViewById(R.id.task_home_add_button)


        // turn into delete lambda?
        val taskClickLambda: (Int) -> Unit = {
           // viewModel.currentTask.value = it
            viewModel.deleteTask(it)
        }
         //   viewAdapter = RecyclerViewAdapter(Array())

            val touchHelper = TouchHelper(taskClickLambda)
        ItemTouchHelper(touchHelper).attachToRecyclerView(task_home_recycler_view)
           // findNavController().navigate(R.id.action_taskHome_to_taskDetail)



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

        // nav buttons
        task_home_home_button.setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboard)
        }

        task_home_add_button.setOnClickListener {
            findNavController().navigate(R.id.action_taskHome_to_taskDetail)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_home, container, false)
    }

}