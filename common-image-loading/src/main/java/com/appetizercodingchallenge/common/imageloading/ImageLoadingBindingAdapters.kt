package com.appetizercodingchallenge.common.imageloading

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation

@BindingAdapter(
    "load", "circleCrop", requireAll = false
)
fun ImageView.loadImage(
    oldPath: String?,
    oldCircleCrop: Boolean?,
    path: String?,
    circleCrop: Boolean?
) {
    if (oldPath != path || oldCircleCrop != circleCrop) {
        load(path) {
            crossfade(true)
            if (circleCrop == true) {
                transformations(CircleCropTransformation())
            }
        }
    }
}
