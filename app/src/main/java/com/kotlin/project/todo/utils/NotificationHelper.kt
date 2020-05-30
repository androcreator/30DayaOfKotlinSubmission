package com.kotlin.project.todo.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.kotlin.project.R


fun createNotification(context: Context){
    val channelId = "${context.packageName}-${context.getString(R.string.app_name)}"
    val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
        setSmallIcon(R.drawable.ic_notification) // 3
        setContentTitle("TODO") // 4
        setContentText("You Todo list have been added..") // 5
        priority = NotificationCompat.PRIORITY_DEFAULT // 7
        setAutoCancel(true) // 8
    }

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(1, notificationBuilder.build())

}