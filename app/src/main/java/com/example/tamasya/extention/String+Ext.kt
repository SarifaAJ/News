package com.example.tamasya.extention

import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toFormattedDate(to:String = "dd MMM yyyy", from:String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): String {
    val inputFormat = SimpleDateFormat(from, Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val outputFormat = SimpleDateFormat(to, Locale.getDefault())
    try {
        val date = inputFormat.parse(this) ?: return this
        return  outputFormat.format(date)
    }catch (e: ParseException){
        return this
    }
}

fun String.getTimeAgo(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    val date = format.parse(this)
    val now = System.currentTimeMillis()
    val diff = kotlin.math.abs(now - date!!.time) / 1000

    return when {
        diff < 60 -> "just now"
        diff < 3600 -> "${diff/60} minutes ago"
        diff < 86400 -> "${diff/3600} hours ago"
        else -> "${diff/86400} days ago"
    }
}
fun String.parseHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}