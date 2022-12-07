package com.example.focusfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton


class medicineDetail : Fragment() {

    lateinit var medicine_detail_home_button : Button
    lateinit var medicine_detail_medication_name_edit : EditText
    lateinit var medicine_detail_generic_name_edit : EditText
    lateinit var medicine_detail_dosage_edit : EditText
    lateinit var medicine_detail_morning_checkbox : CheckBox
    lateinit var medicine_detail_afternoon_checkbox : CheckBox
    lateinit var medicine_detail_evening_checkbox : CheckBox
    lateinit var medicine_detail_food_radio : RadioButton
    lateinit var medicine_detail_no_food_radio : RadioButton
    lateinit var medicine_detail_either_radio : RadioButton
    lateinit var medicine_detail_notes_edit : EditText
    lateinit var medicine_detail_save_button : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicine_detail_home_button = view.findViewById(R.id.medicine_detail_home_button)
        medicine_detail_medication_name_edit = view.findViewById(R.id.medicine_detail_medication_name_edit)
        medicine_detail_generic_name_edit = view.findViewById(R.id.medicine_detail_generic_name_edit)
        medicine_detail_dosage_edit = view.findViewById(R.id.medicine_detail_dosage_edit)
        medicine_detail_morning_checkbox = view.findViewById(R.id.medicine_detail_morning_checkbox)
        medicine_detail_afternoon_checkbox = view.findViewById(R.id.medicine_detail_afternoon_checkbox)
        medicine_detail_evening_checkbox = view.findViewById(R.id.medicine_detail_evening_checkbox)
        medicine_detail_food_radio = view.findViewById(R.id.medicine_detail_food_radio)
        medicine_detail_no_food_radio = view.findViewById(R.id.medicine_detail_no_food_radio)
        medicine_detail_either_radio = view.findViewById(R.id.medicine_detail_either_radio)
        medicine_detail_notes_edit = view.findViewById(R.id.medicine_detail_notes_edit)
        medicine_detail_save_button = view.findViewById(R.id.medicine_detail_save_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_detail, container, false)
    }

}