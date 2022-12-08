package com.example.focusfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class taskHome : Fragment() {

    lateinit var task_home_home_button : Button
    lateinit var task_home_recycler_view : RecyclerView
    lateinit var task_home_add_button : Button

    lateinit var viewManager : RecyclerView.LayoutManager
    lateinit var viewAdapter: RecyclerViewAdapter

    val viewModel : focusFinderViewModel by activityViewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        task_home_home_button = view.findViewById(R.id.task_home_home_button)
        task_home_recycler_view = view.findViewById(R.id.task_home_recycler_view)
        task_home_add_button = view.findViewById(R.id.task_home_add_button)

        viewManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewAdapter =
            viewModel.taskList.value?.let { RecyclerViewAdapter(it, viewModel) }!!  //having an issue

        task_home_recycler_view.layoutManager = viewManager
        task_home_recycler_view.adapter = viewAdapter

        task_home_home_button.setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboard)
        }

        task_home_add_button.setOnClickListener {
            // do VM and DB stuff

            findNavController().navigate(R.id.action_taskHome_to_taskDetail)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_home, container, false)
    }

}