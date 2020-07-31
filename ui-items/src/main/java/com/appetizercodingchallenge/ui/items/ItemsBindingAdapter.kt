package com.appetizercodingchallenge.ui.items

import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

@BindingAdapter("itemPrice", "itemCurrency", requireAll = true)
fun setItemPrice(view: TextView, price: Double, currency: String) {
    val currencyFormatter = DecimalFormat("0.00")
    view.text = SpannableStringBuilder()
        .append(currencyFormatter.format(price))
        .append(" ")
        .bold { append(currency) }
}
