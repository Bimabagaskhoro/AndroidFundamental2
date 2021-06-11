package com.example.submissionfundamental2.receiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.submissionfundamental2.R
import java.text.ParseException
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        private const val TIME_FORMAT = "HH:mm"
        private const val NOTIFY_ID = 1
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "type"
        private const val ID_REPEAT = 101

        private const val PACKAGE_NAME = "com.example.submissionfundamental2"
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "github"
    }

    override fun onReceive(context: Context, intent: Intent) {
        sendNofication(context)
    }

    private fun sendNofication(context: Context) {
        val intent =
            context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_baseline_notifications)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText(context.resources.getString(R.string.notifikasi))
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(mChannel)
        }

        val notify = builder.build()
        notificationManager.notify(NOTIFY_ID, notify)
    }

    fun setRepeatingAlarm(context: Context, type: String, time: String, message: String) {
        if (TIME_FORMAT.isDateInvalid(time)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        val arrayTime = time.split(":".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayTime[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(arrayTime[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEAT, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )
        Toast.makeText(context, "Reminder turn on", Toast.LENGTH_SHORT).show()
    }

    private fun String.isDateInvalid(time: String): Boolean {
        return try {
            val times = SimpleDateFormat(this, Locale.getDefault())
            with(times){
                isLenient = false
                parse(time)
            }
            false
        } catch (e: ParseException) {
            true
        }
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_REPEAT
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, "Repeating alarm Cancle", Toast.LENGTH_SHORT).show()
    }

}