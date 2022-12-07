package com.example.focusfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

class taskHome : Fragment() {

    lateinit var task_home_home_button : Button
    lateinit var task_home_recycler_view : RecyclerView
    lateinit var task_home_add_button : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        task_home_home_button = view.findViewById(R.id.task_home_home_button)
        task_home_recycler_view = view.findViewById(R.id.task_home_recycler_view)
        task_home_add_button = view.findViewById(R.id.task_home_add_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_home, container, false)
    }

}