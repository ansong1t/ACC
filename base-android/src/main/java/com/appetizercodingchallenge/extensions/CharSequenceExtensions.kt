package com.appetizercodingchallenge.extensions

import android.util.Patterns

fun CharSequence?.isEmailValid(): Boolean =
    if (this.isNullOrBlank()) false
    else Patterns.EMAIL_ADDRESS.matcher(this).matches()
