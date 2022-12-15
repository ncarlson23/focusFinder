package com.example.focusfinder

/**
 * medicineHome.kt
 * home page for medicine tracker
 * contains a horizontal scrollable list of medicines
 * user can add, edit, delete medicines from list
 */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class medicineHome : Fragment() {

    // initialize lateinit var variables
    lateinit var medicine_home_home_button: Button
    lateinit var medicine_home_recycler_view: RecyclerView
    lateinit var medicine_home_add_button: Button

    // view model instance
    val viewModel: focusFinderViewModel by activityViewModels()

    // create viewManager and viewAdapter
    lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var viewAdapter: MedicineRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect lateinit vars to corresponding xml components
        medicine_home_add_button = view.findViewById(R.id.medicine_home_add_button)
        medicine_home_recycler_view = view.findViewById(R.id.medicine_home_recycler_view)
        medicine_home_home_button = view.findViewById(R.id.medicine_home_home_button)

        // set on click listener for home button
        // navigates user back to dashboard
        medicine_home_home_button.setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboard)
        }

        // set on click listener for add button
        // navigates user to medicine detail page to add a medicine
        medicine_home_add_button.setOnClickListener {
            // do VM and DB stuff
            findNavController().navigate(R.id.action_medicineHome_to_medicineDetail)
        }

        // medClickLambda
        // navigates user from medicine home to medicine detail page
        val medClickLambda: (Medicine) -> Unit = {
            viewModel.currentMedicine.value = it
            findNavController().navigate(R.id.action_medicineHome_to_medicineDetail)
        }

        // get medicine list from database
        viewModel.getMedicineListFromDB();

        // set up the horizontal view for medicine list on home page
        viewManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        // when user clicks on a medicine it navigates to detail page

        viewAdapter =
            viewModel.medicineList.value?.let { MedicineRecyclerViewAdapter(it, viewModel) }!!
        viewAdapter.clickLambda = medClickLambda
        medicine_home_recycler_view.layoutManager = viewManager
        medicine_home_recycler_view.adapter = viewAdapter

        viewModel.medicineList.observe(viewLifecycleOwner, {
            viewAdapter.medData = it
            viewAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_home, container, false)
    }
}