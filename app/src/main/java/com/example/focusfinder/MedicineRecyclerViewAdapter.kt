package com.example.focusfinder

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MedicineRecyclerViewAdapter(
    var medData: Array<Medicine>,
    val taskViewModel: focusFinderViewModel
) : RecyclerView.Adapter<MedicineRecyclerViewAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val viewItem =
            LayoutInflater.from(parent.context).inflate(R.layout.medicine_item, parent, false)
        return RecyclerViewHolder(viewItem)
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(medData[position])
    }

    override fun getItemCount(): Int {
        return medData.size
    }

    inner class RecyclerViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(medicine: Medicine) {

            itemView.findViewById<TextView>(R.id.medicine_item_generic_name).text = medicine.overCounterName
            itemView.findViewById<TextView>(R.id.medicine_item_medicine_name).text = medicine.officialName
            itemView.findViewById<TextView>(R.id.medicine_item_dosage_amount).text = medicine.dosage
            itemView.findViewById<TextView>(R.id.medicine_item_notes).text = medicine.dosage

            var numTimes = 0
            if (medicine.morning) numTimes++
            if (medicine.afternoon) numTimes++
            if (medicine.evening) numTimes++
            itemView.findViewById<TextView>(R.id.medicine_item_icon_one).text = numTimes.toString()

            if (medicine.food == "Food" || medicine.food == "Either")
                itemView.findViewById<ImageView>(R.id.medicine_item_icon_two).setImageResource(R.drawable.ic_baseline_fastfood_24)
            else itemView.findViewById<ImageView>(R.id.medicine_item_icon_two).setImageResource(R.drawable.ic_baseline_no_food_24)

            if (medicine.morning || medicine.afternoon) {
                if (medicine.evening){
                    itemView.findViewById<ImageView>(R.id.medicine_item_icon_three).setImageResource(R.drawable.ic_morning_night)
                }
                else{
                    itemView.findViewById<ImageView>(R.id.medicine_item_icon_three).setImageResource(R.drawable.ic_day)

                }
            }
            else {
                itemView.findViewById<ImageView>(R.id.medicine_item_icon_three).setImageResource(R.drawable.ic_night)
            }




        }
    }

}