package com.example.focusfinder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

class NotificationsHelper(private val context : Context) {

    val CHANNEL_ID = "com.example.focusfinder.medicine_notifications"
    val CHANNEL_NAME = "medicine"
    val NOTIFICATION_ID = 1

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }



    fun sendNotification() {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_wb_sunny_24)
            .setContentTitle("FocusFinder: Medicine Tracker")
            .setContentText("Reminder to take medicine")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(NOTIFICATION_ID, builder.build())

    }








}