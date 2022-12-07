package com.example.focusfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView


class medicineHome : Fragment() {

    lateinit var medicine_home_home_button : Button
    lateinit var medicine_home_recycler_view : RecyclerView
    lateinit var medicine_home_add_button : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicine_home_add_button = view.findViewById(R.id.medicine_home_add_button)
        medicine_home_recycler_view = view.findViewById(R.id.medicine_home_recycler_view)
        medicine_home_home_button = view.findViewById(R.id.medicine_home_home_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_home, container, false)
    }
}