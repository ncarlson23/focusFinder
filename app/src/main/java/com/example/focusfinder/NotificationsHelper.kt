package com.example.focusfinder

/**
 * NotificationsHelper.kt
 * Initial testing for setting up a notification on Android
 * Note : we use NotificationScheduler() to actually implement our scheduled notifications
 * this was more for learning purposes
 * The following code is written based on Android's documentation for Notifications:
 * https://developer.android.com/reference/android/app/Notification
 * https://developer.android.com/training/scheduling/alarms
 */


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

class NotificationsHelper(private val context: Context) {

    // set up channel id, name, and notification ID
    val CHANNEL_ID = "com.example.focusfinder.medicine_notifications"
    val CHANNEL_NAME = "medicine"
    val NOTIFICATION_ID = 1

    // create notification manager
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // initialize values
    init {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }


    // create and send notifications
    fun sendNotification() {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_wb_sunny_24)
            .setContentTitle("FocusFinder: Medicine Tracker")
            .setContentText("Reminder to take medicine")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(NOTIFICATION_ID, builder.build())

    }


}