package com.example.focusfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val viewModel : focusFinderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.taskDatabase.value = TaskDB.getDBObject(applicationContext)
        viewModel.medicineDatabase.value = MedicineDB.getDBObject(applicationContext)



        findViewById<BottomNavigationView>(R.id.bottom_menu).setOnItemSelectedListener {
            if (it.itemId == R.id.dashboard) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_dashboard)
                return@setOnItemSelectedListener true
            } else if (it.itemId == R.id.med_tracker) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_medicineHome)
                return@setOnItemSelectedListener true
            } else if (it.itemId == R.id.task_tracker) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_taskHome)
                return@setOnItemSelectedListener true
            } else if (it.itemId == R.id.timer) {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_timer)
                return@setOnItemSelectedListener true
            } else {
                return@setOnItemSelectedListener true

            }
        }

    }
}