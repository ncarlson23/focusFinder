package com.example.focusfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton

//https://www.digitalocean.com/community/tutorials/android-date-time-picker-dialog

class taskDetail : Fragment() {

    lateinit var task_detail_home_button : Button
    lateinit var task_detail_task_name : EditText
    lateinit var task_detail_calendar_button : ImageButton
    lateinit var task_detail_time_button : ImageButton
    lateinit var task_detail_low_radio : RadioButton
    lateinit var task_detail_med_radio : RadioButton
    lateinit var task_detail_high_radio : RadioButton
    lateinit var task_detail_notes : EditText
    lateinit var task_detail_save_button : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        task_detail_home_button = view.findViewById(R.id.task_detail_home_button)
        task_detail_task_name = view.findViewById(R.id.task_detail_task_name)
        task_detail_calendar_button = view.findViewById(R.id.task_detail_calendar_button)
        task_detail_time_button = view.findViewById(R.id.task_detail_time_button)
        task_detail_low_radio = view.findViewById(R.id.task_detail_low_radio)
        task_detail_med_radio = view.findViewById(R.id.task_detail_med_radio)
        task_detail_high_radio = view.findViewById(R.id.task_detail_high_radio)
        task_detail_notes = view.findViewById(R.id.task_detail_notes)
        task_detail_save_button = view.findViewById(R.id.task_detail_save_button)

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

}