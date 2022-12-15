package com.example.focusfinder

/**
 * taskDetail.kt
 * task detail fragment
 * when the user wants to add a new task or update an existing entry, navigate to this page
 *
 * TimePicker and DatePicker based off of the following youtube video
 * https://www.youtube.com/watch?v=c6c1giRekB4
 *
 */

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class taskDetail : Fragment() {

    // initalize all lateinit vars
    lateinit var task_detail_home_button: Button
    lateinit var task_detail_task_name: EditText
    lateinit var task_detail_calendar_button: Button  // date
    lateinit var task_detail_time_button: Button  // time
    lateinit var task_detail_low_radio: RadioButton
    lateinit var task_detail_med_radio: RadioButton
    lateinit var task_detail_high_radio: RadioButton
    lateinit var task_detail_notes: EditText
    lateinit var task_detail_save_button: Button
    lateinit var radio_group: RadioGroup


    // get current date
    var time = Calendar.getInstance()
    var timeChanged = false
    var dateChanged = false

    // instance of view model
    val viewModel: focusFinderViewModel by activityViewModels()

    // auto generated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect lateinit vars to xml components
        connectToXML(view)

        if (viewModel.currentTask.value != null) {
            timeChanged = false
            dateChanged = false
            loadData()
        }
        // load in the local data
        if (viewModel.currentTask.value != null) loadData()

        // on click listener for home button
        // navigates back to dashboard
        task_detail_home_button.setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboard)
        }

        // format the date to appear in MM/dd/yyyy form
        var formattedDate = ""
        var sdf = ""
        var cal = Calendar.getInstance()
        val dateSetListener: DatePickerDialog.OnDateSetListener =
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    cal.set(Calendar.YEAR, p1)
                    cal.set(Calendar.MONTH, p2)
                    cal.set(Calendar.DAY_OF_MONTH, p3)

                    formattedDate = "MM/dd/yyyy"

//                cal.toString()
                    Log.d("PENIS", formattedDate)
                    //  formattedDate = "dd/mm/yyyy"
                    sdf = SimpleDateFormat(formattedDate, Locale.US).format(cal.time)

                }
            }

        // format time to appear at 00:00 AM/PM
        var formattedTime = ""
        val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                    formattedTime = when {
                        p1 == 0 -> {
                            if (p2 < 10) {
                                "${p1 + 12}:0${p2} am"
                            } else {
                                "${p1 + 12}: ${p2} am"
                            }
                        }
                        p1 > 12 -> {
                            if (p2 < 10) {
                                "${p1 - 12}:0${p2} pm"
                            } else {
                                "${p1 - 12}:${p2} pm"
                            }
                        }
                        p1 == 12 -> {
                            if (p2 < 10) {
                                "${p1}:0${p2} pm"
                            } else {
                                "${p1}:${p2} pm"
                            }
                        }
                        else -> {
                            if (p2 < 10) {
                                "${p1}:${p2} am"
                            } else {
                                "${p1}:${p2} am"
                            }
                        }
                    }
                }
            }

        // on click listener to pick time for task
        task_detail_time_button.setOnClickListener {
            timeChanged = true
            val timePicker: TimePickerDialog = TimePickerDialog(
                this.context,
                timePickerDialogListener,
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE),
                false
            )
            timePicker.show()
        }


        // on click listener to pick date for task
        task_detail_calendar_button.setOnClickListener {
            dateChanged = true
            val datePicker: DatePickerDialog = DatePickerDialog(
                this.requireContext(),
                dateSetListener,
                time.get(Calendar.YEAR),
                time.get(Calendar.MONTH),
                time.get(Calendar.DAY_OF_MONTH),

                )
            datePicker.show()
        }

        // save button saves the task with all the inputted data
        task_detail_save_button.setOnClickListener {
            if (task_detail_task_name.text.isNotEmpty() && viewModel.currentTask.value == null) {
                addNewTask(sdf, formattedTime)
            }
            if (viewModel.currentTask.value != null) {
                if (!dateChanged) sdf = viewModel.currentTask.value!!.date
                if (!timeChanged) formattedTime = viewModel.currentTask.value!!.time
                updateTaskInDatabase(sdf, formattedTime)
            }

            viewModel.currentTask.value = null
            findNavController().navigate(R.id.action_global_taskHome)
        }

    }

    // updateTaskInDatabse
    // make sure the value of task priority is calculated correctly
    private fun updateTaskInDatabase(sdf: String, formattedTime: String) {
        viewModel.currentTask.value?.taskItem = task_detail_task_name.text.toString()
        viewModel.currentTask.value?.date = sdf
        viewModel.currentTask.value?.time = formattedTime
        viewModel.currentTask.value?.priority =
            if (task_detail_low_radio.isChecked) {
                1
            } else if (task_detail_med_radio.isChecked) {
                2
            } else if (task_detail_high_radio.isChecked) {
                3
            } else {
                0
            }
        viewModel.currentTask.value?.note = task_detail_notes.text.toString()
        viewModel.updateTask(viewModel.currentTask.value!!)
    }

    // add new task
    private fun addNewTask(sdf: String, formattedTime: String) {
        val task = Task()
        task.taskItem = task_detail_task_name.text.toString()
        task.note = task_detail_notes.text.toString()
        task.date = sdf
        task.time = formattedTime
        task.priority = if (task_detail_low_radio.isChecked) {
            1
        } else if (task_detail_med_radio.isChecked) {
            2
        } else if (task_detail_high_radio.isChecked) {
            3
        } else {
            0
        }

        viewModel.addNewTask(task)

    }

    // load in the data for the current task
    private fun loadData() {
        task_detail_task_name.setText(viewModel.currentTask.value!!.taskItem)
        task_detail_notes.setText(viewModel.currentTask.value!!.note)

        if (viewModel.currentTask.value!!.priority == 1) {
            task_detail_low_radio.isChecked = true
        } else if (viewModel.currentTask.value!!.priority == 2) {
            task_detail_med_radio.isChecked = true
        } else if (viewModel.currentTask.value!!.priority == 3) {
            task_detail_high_radio.isChecked = true
        }
    }

    // connect lateinit vars to corresponding xml components
    private fun connectToXML(view: View) {
        task_detail_home_button = view.findViewById(R.id.task_detail_home_button)
        task_detail_task_name = view.findViewById(R.id.task_detail_task_name)
        task_detail_calendar_button = view.findViewById(R.id.task_detail_calendar_button) // date
        task_detail_time_button = view.findViewById(R.id.task_detail_time_button)  // time
        task_detail_low_radio = view.findViewById(R.id.task_detail_low_radio)
        task_detail_med_radio = view.findViewById(R.id.task_detail_med_radio)
        task_detail_high_radio = view.findViewById(R.id.task_detail_high_radio)
        task_detail_notes = view.findViewById(R.id.task_detail_notes)
        task_detail_save_button = view.findViewById(R.id.task_detail_save_button)
        radio_group = view.findViewById(R.id.task_detail_priority_radio_group)
    }

    // autogenerated
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

}