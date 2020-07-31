package com.appetizercodingchallenge.extensions

import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu

inline fun showPopupMenu(
    anchor: View,
    anchorGravity: Int = Gravity.BOTTOM,
    items: Array<String>,
    crossinline onItemClicked: (MenuItem) -> Boolean
) {
    PopupMenu(anchor.context, anchor, anchorGravity).apply {
        items.forEach { menu.add(it) }
        setOnMenuItemClickListener {
            onItemClicked(it)
        }
    }.show()
}
