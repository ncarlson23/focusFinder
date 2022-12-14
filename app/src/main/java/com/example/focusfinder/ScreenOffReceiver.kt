package com.example.focusfinder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ScreenOffReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1?.action == Intent.ACTION_SCREEN_OFF){
            val notificationsHelper = p0?.let { NotificationsHelper(it) }
            if (notificationsHelper != null) {
                notificationsHelper.sendNotification()
            }
        }
    }
}