package com.brainxtech.extensions.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import androidx.core.app.NotificationManagerCompat

object NotificationUtil {

    fun Context.createNotificationChannelForOreoAndAbove(
        appName:Int,
        channelId: String,
        channelName:String,
        setSound: Boolean = false,
        notificationImportance: Int = NotificationManager.IMPORTANCE_NONE
    ): NotificationManager {

        // Create the NotificationChannel
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val mChannel = NotificationChannel(
            channelId,
            channelName,
            notificationImportance
        ).apply {
            this.description = getString(appName)
            setShowBadge(true)
        }
        if (setSound){
            mChannel.setSound(soundUri, null)
        }
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(mChannel)
        return notificationManager
    }

    fun Context.cancelAllNotifications() {
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.cancelAll()
    }

}