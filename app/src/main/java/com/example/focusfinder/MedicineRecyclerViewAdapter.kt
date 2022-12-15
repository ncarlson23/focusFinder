package com.example.focusfinder

/**
 * MedicineRecyclerViewAdapter.kt
 * recycler view adapter for the medicine data
 * update the recycler view when medicines are added, updated, deleted
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MedicineRecyclerViewAdapter(
    // array representing medicine data
    var medData: Array<Medicine>,
    // instance of view model
    val medViewModel: focusFinderViewModel
) : RecyclerView.Adapter<MedicineRecyclerViewAdapter.RecyclerViewHolder>() {

    // click lambda initialized
    lateinit var clickLambda: (Medicine) -> Unit

    // auto generated
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.medicine_item, parent, false)
        return RecyclerViewHolder(viewItem, clickLambda)
    }

    // onBindViewHolder
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(medData[position], clickLambda)
    }

    // getItemCount
    override fun getItemCount(): Int {
        return medData.size
    }

    inner class RecyclerViewHolder(
        itemView: View, val clickLambda: (Medicine) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(medicine: Medicine, cickLambda: (Medicine) -> Unit) {

            // connect medicine parameters vars to corresponding xml components
            itemView.findViewById<TextView>(R.id.medicine_item_generic_name).text =
                medicine.overCounterName
            itemView.findViewById<TextView>(R.id.medicine_item_medicine_name).text =
                medicine.officialName
            itemView.findViewById<TextView>(R.id.medicine_item_dosage_amount).text = medicine.dosage
            itemView.findViewById<TextView>(R.id.medicine_item_notes).text = medicine.notes


            // calculate number of times a day user needs to take medicine based on user input
            // based on this we know what icon to use to represent when user should take medicine
            var numTimes = 0
            if (medicine.morning) numTimes++
            if (medicine.afternoon) numTimes++
            if (medicine.evening) numTimes++
            itemView.findViewById<TextView>(R.id.medicine_item_icon_one).text = numTimes.toString()

            // pick appropriate icon depending on whether medicine is taken with or without food
            // based on what user picks
            if (medicine.food == "Food" || medicine.food == "Either")
                itemView.findViewById<ImageView>(R.id.medicine_item_icon_two)
                    .setImageResource(R.drawable.ic_baseline_fastfood_24)
            else itemView.findViewById<ImageView>(R.id.medicine_item_icon_two)
                .setImageResource(R.drawable.ic_baseline_no_food_24)

            // pick appropriate icon depending on whether medicine is taken in the morning/afternoon/evening
            // based on what user picks
            if (medicine.morning || medicine.afternoon) {
                if (medicine.evening) {
                    itemView.findViewById<ImageView>(R.id.medicine_item_icon_three)
                        .setImageResource(R.drawable.ic_morning_night)
                } else {
                    itemView.findViewById<ImageView>(R.id.medicine_item_icon_three)
                        .setImageResource(R.drawable.ic_day)
                }
            } else {
                itemView.findViewById<ImageView>(R.id.medicine_item_icon_three)
                    .setImageResource(R.drawable.ic_night)
            }


            itemView.setOnClickListener {
                clickLambda(medicine)
            }

            // on click listener to delete current medicine
            itemView.findViewById<Button>(R.id.medicine_item_delete).setOnClickListener {
                // delete current medicine item
                medViewModel.deleteMedicine(medicine)
            }


        }
    }

}