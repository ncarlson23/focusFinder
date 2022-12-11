package com.example.focusfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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


        }
    }

}