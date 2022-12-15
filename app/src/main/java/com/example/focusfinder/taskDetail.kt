package com.example.focusfinder

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

//https://www.digitalocean.com/community/tutorials/android-date-time-picker-dialog

class taskDetail : Fragment() {

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

    var time = Calendar.getInstance()


    val viewModel: focusFinderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectToXML(view)

        if (viewModel.currentTask.value != null) loadData()

        task_detail_home_button.setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboard)
        }

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

        task_detail_time_button.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(
                this.context,
                timePickerDialogListener,
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE),
                false
            )
            timePicker.show()
        }


        task_detail_calendar_button.setOnClickListener {
            val datePicker: DatePickerDialog = DatePickerDialog(
                this.requireContext(),
                dateSetListener,
                time.get(Calendar.YEAR),
                time.get(Calendar.MONTH),
                time.get(Calendar.DAY_OF_MONTH),

                )
            datePicker.show()
        }

        // just trying with name and notes, deal with buttons later
        task_detail_save_button.setOnClickListener {
            if (task_detail_task_name.text.isNotEmpty() && viewModel.currentTask.value == null) {
                addNewTask(sdf, formattedTime)
            }
            if (viewModel.currentTask.value != null){
                updateTaskInDatabase(sdf, formattedTime)
            }

            viewModel.currentTask.value = null
            findNavController().navigate(R.id.action_global_taskHome)
        }

    }

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

}