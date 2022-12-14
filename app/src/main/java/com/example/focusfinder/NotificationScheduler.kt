package com.example.focusfinder

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.focusfinder.com.example.focusfinder.NotificationPublisher
import com.example.focusfinder.com.example.focusfinder.NotificationPublisher.Companion.NOTIFICATION_ID
import com.example.focusfinder.com.example.focusfinder.NotificationPublisher.Companion.NOTIFICATION_TITLE
import com.example.focusfinder.com.example.focusfinder.NotificationPublisher.Companion.NOTIFICATION_TEXT

import java.util.*

class NotificationScheduler(context: Context) {

    companion object {
        // The request code for the pending intent
        private const val REQUEST_CODE = 1

        // The ID of the notification channel
        private const val CHANNEL_ID = "TaskNotifications"

        // The name of the notification channel
        private const val CHANNEL_NAME = "Task Notifications"

        // The default notification title
        private const val DEFAULT_NOTIFICATION_TITLE = "Task Reminder"

        // The default notification text
        private const val DEFAULT_NOTIFICATION_TEXT = "This is a reminder for a task"
    }

    // The context to use for the pending intent
    private val context: Context

    // The AlarmManager to use for scheduling the notifications
    private val alarmManager: AlarmManager

    init {
        // Get the context and AlarmManager objects
        this.context = context.applicationContext
        this.alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Create the notification channel
        createNotificationChannel()
    }

    // Schedules a notification to be sent at the specified time
    fun scheduleNotification(time: Calendar, title: String, text: String) {
        // Create a pending intent to be triggered when the notification is sent
        val intent = Intent(context, NotificationPublisher::class.java)
        intent.putExtra(NotificationPublisher.NOTIFICATION_ID, REQUEST_CODE)
        intent.putExtra(NotificationPublisher.NOTIFICATION_TITLE, title)
        intent.putExtra(NotificationPublisher.NOTIFICATION_TEXT, text)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Set the alarm to trigger the pending intent at the specified time
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            time.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    // Creates the notification channel for Android 8.0 (Oreo) and later
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}


