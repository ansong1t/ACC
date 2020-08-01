package com.appetizercodingchallenge.common.navigation

import androidx.core.net.toUri
import androidx.navigation.NavOptions

fun tvShowDeeplink(collectionId: Long) =
    "com.appetizercodingchallenge://tv-show/$collectionId".toUri()

fun defaultNavAnimation(block: ((NavOptions.Builder) -> Unit)? = null) =
    NavOptions.Builder().apply {
        block?.invoke(this)
    }.setEnterAnim(R.anim.acc_enter_anim)
        .setExitAnim(R.anim.acc_exit_anim)
        .setPopEnterAnim(R.anim.acc_pop_enter_anim)
        .setPopExitAnim(R.anim.acc_pop_exit_anim)
        .build()
