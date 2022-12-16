package com.example.focusfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import org.w3c.dom.Text
import java.util.*

/**
 * Dashboard.kt
 * Fragment for the dashboard (home) screen of app
 * Displays welcome message, overview of tasks to be completed and medicines to take
 *
 */

class Dashboard : Fragment() {


    // initialize variables
    lateinit var num_meds: TextView
    lateinit var total_tasks: TextView
    lateinit var tasks_today: TextView
    lateinit var medsList: TextView
    lateinit var task_name: TextView
    lateinit var task_priority: TextView
    lateinit var task_date: TextView


    // create viewModel instance
    val viewModel: focusFinderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
        // get task list and medicine list from databases

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set variables set equal to corresponding xml component
        num_meds = view.findViewById<TextView>(R.id.dashboard_num_meds)
        total_tasks = view.findViewById<TextView>(R.id.dashboard_total_tasks)
        medsList = view.findViewById<TextView>(R.id.dashboard_meds_list)
        task_name = view.findViewById<TextView>(R.id.dashboard_task_name)
        task_priority = view.findViewById<TextView>(R.id.dashboard_task_priority)
        task_date = view.findViewById<TextView>(R.id.dashboard_task_date)

        view.findViewById<LinearLayout>(R.id.dashboard_medicine_title_layout).setOnClickListener {
            findNavController().navigate(R.id.action_global_medicineHome)
        }
        view.findViewById<LinearLayout>(R.id.dashboard_tasks_title_layout).setOnClickListener {
            findNavController().navigate(R.id.action_global_taskHome)
        }

        // get task list and medicine list from databases
        viewModel.getTaskListFromDB()
        viewModel.getMedicineListFromDB()


        total_tasks.text =
            "You have " + viewModel.taskList.value!!.size + " total tasks to complete\nYour highest priority task is:"
        num_meds.text = "You have " + viewModel.medicineList.value!!.size + " meds to take today"

        // create list of three meds to display
        var meds = ""
        var numMeds = 0
        for (medicine in viewModel.medicineList.value!!) {
            meds += medicine.overCounterName
            if (numMeds != 2) meds += "\n"
            if (numMeds == 2) break
            numMeds++
        }
        medsList.text = meds
        if (viewModel.medicineList.value!!.isEmpty()){
            medsList.text = "You have no current medications "
        }

        // display highest priority task
        if (viewModel.taskList.value!!.isNotEmpty()) {
            // get highest priority task to display on dashboard
            var topTask = viewModel.taskList.value!![0]
            task_name.text = topTask.taskItem
            task_date.text = topTask.date
            var p = ""
            if (topTask.priority == 1) p = "!"
            if (topTask.priority == 2) p = "!!"
            if (topTask.priority == 3) p = "!!!"
            task_priority.text = p


        } else {
            task_name.text = "No upcoming tasks"
            task_priority.text = ""
            task_date.text = ""

        }


    }


}
