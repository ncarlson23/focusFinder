package com.example.focusfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

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

//        val notificationsHelper = NotificationsHelper(this)
//        notificationsHelper.sendNotification()

        val notificationScheduler = NotificationScheduler(this)


        val time = Calendar.getInstance()

        time.set(Calendar.HOUR_OF_DAY,18 )
        time.set(Calendar.MINUTE, 5)
        time.set(Calendar.SECOND, 0)


//        if(viewModel.currentMedicine.value?.morning == true) {
//            time.set(Calendar.HOUR_OF_DAY, 9)
//            time.set(Calendar.MINUTE, 0)
//            time.set(Calendar.SECOND, 0)
//        } else if (viewModel.currentMedicine.value?.afternoon == true) {
//            time.set(Calendar.HOUR_OF_DAY, 12)
//            time.set(Calendar.MINUTE, 0)
//            time.set(Calendar.SECOND, 0)
//        }else {
//            time.set(Calendar.HOUR_OF_DAY,18 )
//            time.set(Calendar.MINUTE, 0)
//            time.set(Calendar.SECOND, 0)
//        }


        notificationScheduler.scheduleNotification(time, "Task Reminder", "This is a reminder for a task")


    }
}