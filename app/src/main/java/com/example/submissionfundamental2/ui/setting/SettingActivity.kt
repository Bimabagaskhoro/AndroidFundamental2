package com.example.submissionfundamental2.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submissionfundamental2.R
import com.example.submissionfundamental2.data.Reminder
import com.example.submissionfundamental2.databinding.ActivitySettingBinding
import com.example.submissionfundamental2.preference.ReminderPreference
import com.example.submissionfundamental2.receiver.AlarmReceiver

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver

    companion object {
        private const val ALARM_TYPE = "RepeatingAlarm"
        private const val ALARM_TIME = "02.15"
        private const val ALARM_MESSAGE = "reminder"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.set_reminder)

        val reminderPreference = ReminderPreference(this)
        reminderPreference.getReminder().isReminderss.also { binding.switch1.isChecked = it }
        alarmReceiver = AlarmReceiver()
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this,
                    ALARM_TYPE, ALARM_TIME, ALARM_MESSAGE)
            } else {
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPreference = ReminderPreference(this)
        reminder = Reminder()

        reminder.isReminderss = state
        reminderPreference.setReminder(reminder)
    }
}