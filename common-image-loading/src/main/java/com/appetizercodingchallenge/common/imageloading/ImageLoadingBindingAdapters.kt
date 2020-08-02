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
    if (oldPath != path) {
        load(path) {
            crossfade(true)
            placeholder(R.drawable.image_loading_placeholder)
            error(R.drawable.image_loading_placeholder)
            if (oldCircleCrop != circleCrop && circleCrop == true) {
                transformations(CircleCropTransformation())
            }
        }
    }
}
