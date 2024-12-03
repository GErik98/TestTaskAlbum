package com.example.testtask.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("formattedDate")
fun setFormattedDate(view: TextView, date: String?) {
    date?.let {
        try {
            // Parse the original date format
            val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            originalFormat.timeZone = TimeZone.getTimeZone("UTC")
            val parsedDate = originalFormat.parse(it)

            // Format to the desired date format
            val targetFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val formattedDate = parsedDate?.let { targetFormat.format(it) }

            view.text = formattedDate ?: it // Fallback to original if parsing fails
        } catch (e: Exception) {
            view.text = it // Fallback to original if an error occurs
        }
    }
}
