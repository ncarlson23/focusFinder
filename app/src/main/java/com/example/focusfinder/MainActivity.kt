package com.example.focusfinder

/**
 * MainActivity.kt
 * sets up the navigation between pages using navigate() and setOnItemSelectedListener()
 * navigate between different fragments
 * sets up the home menu button
 * implements the push notifications scheduler
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    // view model instance
    val viewModel: focusFinderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.taskDatabase.value = TaskDB.getDBObject(applicationContext)
        viewModel.medicineDatabase.value = MedicineDB.getDBObject(applicationContext)


        /**
         * handle navigation between fragments
         * handle home menu button navigation
         */
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

        /**
         * Handle push notification scheduler
         * 3 notifications:
         * one in the morning for medicine
         * one in the afternoon for medicine
         * one in the evening for medicine
         */
        val notificationScheduler = NotificationScheduler(this)


        val time = Calendar.getInstance()

        time.set(Calendar.HOUR_OF_DAY, 8)
        time.set(Calendar.MINUTE, 59)
        time.set(Calendar.SECOND, 0)

        val time2 = Calendar.getInstance()

        time.set(Calendar.HOUR_OF_DAY, 12)
        time.set(Calendar.MINUTE, 59)
        time.set(Calendar.SECOND, 0)

        val time3 = Calendar.getInstance()

        time.set(Calendar.HOUR_OF_DAY, 18)
        time.set(Calendar.MINUTE, 59)
        time.set(Calendar.SECOND, 0)




        notificationScheduler.scheduleNotification(
            time,
            "Medicine Reminder",
            "This is a reminder to take any morning medications"
        )
        notificationScheduler.scheduleNotification(
            time2,
            "Medicine Reminder",
            "This is a reminder to take any evening medications"
        )
        notificationScheduler.scheduleNotification(
            time3,
            "Medicine Reminder",
            "This is a reminder to take any night-time medications"
        )


    }
}