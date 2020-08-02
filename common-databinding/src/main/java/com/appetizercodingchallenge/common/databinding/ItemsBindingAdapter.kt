package com.appetizercodingchallenge.common.databinding

import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import com.appetizercodingchallenge.util.formatDate
import com.appetizercodingchallenge.util.getDuration
import java.text.DecimalFormat

@BindingAdapter("itemPrice", "itemCurrency", requireAll = true)
fun setItemPrice(view: TextView, price: Double, currency: String) {
    val currencyFormatter = DecimalFormat("0.00")
    view.text = SpannableStringBuilder()
        .append(currencyFormatter.format(price))
        .append(" ")
        .bold { append(currency) }
}

@BindingAdapter("year")
fun setYear(view: TextView, releaseDate: String) {
    val year = formatDate(releaseDate, outputFormat = "yyyy")
    view.text = year
}

@BindingAdapter("duration")
fun duration(view: TextView, millis: Long) {
    val duration = getDuration(millis)
    view.text = duration
}
