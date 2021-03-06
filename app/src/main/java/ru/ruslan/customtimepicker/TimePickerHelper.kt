package ru.ruslan.customtimepicker

import android.app.TimePickerDialog
import android.content.Context
import java.util.*

class TimePickerHelper(
    context: Context,
    is24HourView: Boolean,
    isSpinnerType: Boolean = false
) {
    private var dialog: IntervalTimePickerDialog
    private var callback: Callback? = null
    private val listener = TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
        callback?.onTimeSelected(hourOfDay, minute)
    }

    interface Callback {
        fun onTimeSelected(hourOfDay: Int, minute: Int)
    }

    init {
        val style = if (isSpinnerType) R.style.SpinnerTimePickerDialog else 0
        val cal = Calendar.getInstance()
        dialog = IntervalTimePickerDialog(
            context, style, listener,
            cal[Calendar.HOUR],
            cal[Calendar.MINUTE],
            is24HourView
        )
    }

    fun showDialog(hourOfDay: Int, minute: Int, callback: Callback?) {
        this.callback = callback
        dialog.updateTime(hourOfDay, minute)
        dialog.show()
    }

}