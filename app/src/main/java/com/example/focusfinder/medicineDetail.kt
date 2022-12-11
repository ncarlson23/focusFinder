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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController


class medicineDetail : Fragment() {

    lateinit var medicine_detail_home_button: Button
    lateinit var medicine_detail_medication_name_edit: EditText
    lateinit var medicine_detail_generic_name_edit: EditText
    lateinit var medicine_detail_dosage_edit: EditText
    lateinit var medicine_detail_morning_checkbox: CheckBox
    lateinit var medicine_detail_afternoon_checkbox: CheckBox
    lateinit var medicine_detail_evening_checkbox: CheckBox
    lateinit var medicine_detail_food_radio: RadioButton
    lateinit var medicine_detail_no_food_radio: RadioButton
    lateinit var medicine_detail_either_radio: RadioButton
    lateinit var medicine_detail_notes_edit: EditText
    lateinit var medicine_detail_save_button: Button

    val viewModel: focusFinderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicine_detail_home_button = view.findViewById(R.id.medicine_detail_home_button)
        medicine_detail_medication_name_edit =
            view.findViewById(R.id.medicine_detail_medication_name_edit)
        medicine_detail_generic_name_edit =
            view.findViewById(R.id.medicine_detail_generic_name_edit)
        medicine_detail_dosage_edit = view.findViewById(R.id.medicine_detail_dosage_edit)
        medicine_detail_morning_checkbox = view.findViewById(R.id.medicine_detail_morning_checkbox)
        medicine_detail_afternoon_checkbox =
            view.findViewById(R.id.medicine_detail_afternoon_checkbox)
        medicine_detail_evening_checkbox = view.findViewById(R.id.medicine_detail_evening_checkbox)
        medicine_detail_food_radio = view.findViewById(R.id.medicine_detail_food_radio)
        medicine_detail_no_food_radio = view.findViewById(R.id.medicine_detail_no_food_radio)
        medicine_detail_either_radio = view.findViewById(R.id.medicine_detail_either_radio)
        medicine_detail_notes_edit = view.findViewById(R.id.medicine_detail_notes_edit)
        medicine_detail_save_button = view.findViewById(R.id.medicine_detail_save_button)

        if (viewModel.currentMedicine.value != null) {
            medicine_detail_medication_name_edit.setText(viewModel.currentMedicine.value?.officialName)
            medicine_detail_generic_name_edit.setText(viewModel.currentMedicine.value?.overCounterName)
            medicine_detail_dosage_edit.setText(viewModel.currentMedicine.value?.dosage)
            medicine_detail_notes_edit.setText(viewModel.currentMedicine.value?.notes)

            if (viewModel.currentMedicine.value!!.morning) medicine_detail_morning_checkbox.isChecked =
                true
            if (viewModel.currentMedicine.value!!.afternoon) medicine_detail_afternoon_checkbox.isChecked =
                true
            if (viewModel.currentMedicine.value!!.evening) medicine_detail_evening_checkbox.isChecked =
                true

            if (viewModel.currentMedicine.value!!.food == "Food") medicine_detail_food_radio.isChecked =
                true
            if (viewModel.currentMedicine.value!!.food == " No Food") medicine_detail_no_food_radio.isChecked =
                true
            if (viewModel.currentMedicine.value!!.food == "Either") medicine_detail_either_radio.isChecked =
                true


        }


        medicine_detail_home_button.setOnClickListener {
            viewModel.currentMedicine.value = null
            findNavController().navigate(R.id.action_global_dashboard)
        }

        medicine_detail_save_button.setOnClickListener {

            var medicine = Medicine()
            if (medicine_detail_generic_name_edit.text != null) {
                medicine.overCounterName = medicine_detail_generic_name_edit.text.toString()
                medicine.officialName = medicine_detail_medication_name_edit.text.toString()
                var withFood = ""
                if (medicine_detail_food_radio.isChecked) withFood = "Food"
                if (medicine_detail_no_food_radio.isChecked) withFood = "No Food"
                if (medicine_detail_either_radio.isChecked) withFood = "Either"
                medicine.food = withFood
                medicine.dosage = medicine_detail_dosage_edit.text.toString()
                medicine.morning = medicine_detail_morning_checkbox.isChecked
                medicine.afternoon = medicine_detail_afternoon_checkbox.isChecked
                medicine.evening = medicine_detail_evening_checkbox.isChecked
                medicine.notes = medicine_detail_notes_edit.text.toString()

                viewModel.addNewMedicine(medicine)
            }
            viewModel.currentMedicine.value = null
            findNavController().navigate(R.id.action_global_medicineHome)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_detail, container, false)
    }

}