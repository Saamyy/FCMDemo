package itworx.com.fcmdemo.firebaseServices

import android.annotation.SuppressLint
import android.app.Notification
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import itworx.com.fcmdemo.MainActivity
import itworx.com.fcmdemo.R
import android.app.NotificationChannel
import android.os.Build
import itworx.com.fcmdemo.BuildConfig


class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "FirebaseMessagingService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        val id = System.currentTimeMillis().toInt()


        val builder = NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)

        //_____REDIRECTING PAGE WHEN NOTIFICATION CLICKS_____
        val resultIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent
            .getActivity(
                this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        builder.setContentIntent(pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_HIGH
            val channelID = BuildConfig.APPLICATION_ID
            val channel = NotificationChannel(
                getString(R.string.default_notification_channel_id),
                BuildConfig.APPLICATION_ID,
                importance
            )
            channel.description = channelID
            val notificationManager = getSystemService(NotificationManager::class.java)
            //assert notificationManager != null;
            notificationManager.createNotificationChannel(channel)
        }
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(id, builder.build())
    }

}