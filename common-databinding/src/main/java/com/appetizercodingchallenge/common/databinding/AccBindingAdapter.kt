package com.appetizercodingchallenge.common.databinding

import android.content.res.Resources
import android.graphics.Outline
import android.graphics.Typeface
import android.os.Build
import android.text.method.LinkMovementMethod
import android.view.Gravity
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appetizercodingchallenge.extensions.resolveThemeColor
import com.appetizercodingchallenge.extensions.resolveThemeReferenceResId
import com.appetizercodingchallenge.ui.MaxLinesToggleClickListener
import com.appetizercodingchallenge.ui.ScrimUtil
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.roundToInt

@BindingAdapter("visibleIfNotNull")
fun visibleIfNotNull(view: View, target: Any?) {
    view.isVisible = target != null
}

@BindingAdapter("visible")
fun visible(view: View, value: Boolean) {
    view.isVisible = value
}

@BindingAdapter("textOrGoneIfEmpty")
fun textOrGoneIfEmpty(view: TextView, s: CharSequence?) {
    view.text = s
    view.isGone = s.isNullOrEmpty()
}

@BindingAdapter("textHtml")
fun textHtml(view: TextView, s: String) {
    view.text = HtmlCompat.fromHtml(
        s,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )
    view.movementMethod = LinkMovementMethod.getInstance()
}

@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, value: Any?) {
    view.isGone = value == null
}

@BindingAdapter("srcRes")
fun imageViewSrcRes(view: ImageView, drawableRes: Int) {
    if (drawableRes != 0) {
        view.setImageResource(drawableRes)
    } else {
        view.setImageDrawable(null)
    }
}

@BindingAdapter("maxLinesToggle")
fun maxLinesClickListener(view: TextView, oldCollapsedMaxLines: Int, newCollapsedMaxLines: Int) {
    if (oldCollapsedMaxLines != newCollapsedMaxLines) {
        // Default to collapsed
        view.maxLines = newCollapsedMaxLines
        // Now set click listener
        view.setOnClickListener(MaxLinesToggleClickListener(newCollapsedMaxLines))
    }
}

@BindingAdapter("backgroundScrim")
fun backgroundScrim(view: View, oldColor: Int, color: Int) {
    if (oldColor != color) {
        view.background = ScrimUtil.makeCubicGradientScrimDrawable(color, 16, Gravity.BOTTOM)
    }
}

@BindingAdapter("foregroundScrim")
fun foregroundScrim(view: View, oldColor: Int, color: Int) {
    if (oldColor != color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.foreground = ScrimUtil.makeCubicGradientScrimDrawable(color, 16, Gravity.BOTTOM)
        }
    }
}

@BindingAdapter("topCornerOutlineProvider")
fun topCornerOutlineProvider(view: View, oldRadius: Float, radius: Float) {
    view.clipToOutline = true
    if (oldRadius != radius) {
        view.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height + radius.roundToInt(), radius)
            }
        }
    }
}

@BindingAdapter("roundedCornerOutlineProvider")
fun roundedCornerOutlineProvider(view: View, oldRadius: Float, radius: Float) {
    view.clipToOutline = true
    if (oldRadius != radius) {
        view.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, radius)
            }
        }
    }
}

@BindingAdapter("textAppearanceAttr")
fun textAppearanceAttr(
    view: TextView,
    oldTextAppearanceStyleAttr: Int,
    textAppearanceStyleAttr: Int
) {
    if (oldTextAppearanceStyleAttr != textAppearanceStyleAttr) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setTextAppearance(view.context.resolveThemeReferenceResId(textAppearanceStyleAttr))
        }
    }
}

@BindingAdapter("textColorAttr")
fun textColorAttr(
    view: TextView,
    oldTextColorAttr: Int,
    textColorAttr: Int
) {
    if (oldTextColorAttr != textColorAttr) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setTextColor(view.context.resolveThemeColor(textColorAttr))
        }
    }
}

@BindingAdapter("fontFamily")
fun fontFamily(view: TextView, oldFontFamily: Int, fontFamily: Int) {
    if (oldFontFamily != fontFamily) {
        view.typeface = try {
            ResourcesCompat.getFont(view.context, fontFamily)
        } catch (nfe: Resources.NotFoundException) {
            null
        } ?: Typeface.DEFAULT
    }
}

@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter("isRefreshing")
fun isRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean) {
    view.isRefreshing = isRefreshing
}

@BindingAdapter("endIconOnClick")
fun setEndIconOnClickListener(view: TextInputLayout, onClickListener: View.OnClickListener) {
    view.setEndIconOnClickListener(onClickListener)
}
