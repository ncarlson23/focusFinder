package com.example.focusfinder

/**
 * NotificationPusblisher.kt
 * The following code is written based on Android's documentation for Notifications:
 * https://developer.android.com/reference/android/app/Notification
 */

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationPublisher : BroadcastReceiver() {

    companion object {
        // The key for the notification ID in the intent extras
        const val NOTIFICATION_ID = "notification-id"

        // The key for the notification title in the intent extras
        const val NOTIFICATION_TITLE = "notification-title"

        // The key for the notification text in the intent extras
        const val NOTIFICATION_TEXT = "notification-text"

        // The ID of the notification channel
        private const val CHANNEL_ID = "TaskNotifications"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // Get the notification data from the intent extras
        val notificationId = intent.getIntExtra(NOTIFICATION_ID, 0)
        val DEFAULT_NOTIFICATION_TITLE = "medicine reminder"
        val notificationTitle =
            intent.getStringExtra(NOTIFICATION_TITLE) ?: DEFAULT_NOTIFICATION_TITLE
        val DEFAULT_NOTIFICATION_TEXT = "this is a reminder to take your medicine"
        val notificationText = intent.getStringExtra(NOTIFICATION_TEXT) ?: DEFAULT_NOTIFICATION_TEXT

        // Use the NotificationCompat.Builder to build the notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_wb_sunny_24)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Use the NotificationManager to send the notification
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, builder.build())
    }
}
