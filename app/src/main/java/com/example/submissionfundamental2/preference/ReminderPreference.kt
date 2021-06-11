package com.example.submissionfundamental2.preference

import android.content.Context
import com.example.submissionfundamental2.data.Reminder

class ReminderPreference(context: Context) {
    companion object {
        const val PREFERENCE_NAME = "reminder_preference"
        private const val REMINDER = "isReminder"
    }

    private val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setReminder(value: Reminder) {
        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isReminderss)
        editor.apply()
    }

    fun getReminder(): Reminder {
        val model = Reminder()
        model.isReminderss = preference.getBoolean(REMINDER, false)
        return model
    }
}