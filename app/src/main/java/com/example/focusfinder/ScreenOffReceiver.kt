package com.example.focusfinder

/**
 * ScreenOffReceiver.kt
 * so that notifications are received even when the app is not running
 * The following code is written based on Android's documentation for Notifications:
 * https://developer.android.com/reference/android/app/Notification
 * https://developer.android.com/training/scheduling/alarms
 */

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ScreenOffReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1?.action == Intent.ACTION_SCREEN_OFF) {
            val notificationsHelper = p0?.let { NotificationsHelper(it) }
            if (notificationsHelper != null) {
                notificationsHelper.sendNotification()
            }
        }
    }
}