package com.appetizercodingchallenge.common.navigation

import androidx.core.net.toUri
import androidx.navigation.NavOptions

fun eventDetailsDeepLink(eventId: Long) = "com.questrewards://event/$eventId".toUri()

fun userAddressDeepLink(addressId: Long) = "com.questrewards://user/address/$addressId".toUri()

fun editUserInterestDeepLink() = "com.questrewards://user/interests/edit".toUri()

fun editUserInfoDeepLink() = "com.questrewards://user/personalinfo/edit".toUri()

fun editMobileNumberDeepLink() = "com.questrewards://user/mobile-number/edit".toUri()

fun verifyOtpDeepLink(countryCode: String, phoneNumber: String) =
    "com.questrewards://user/verify-otp?countryCode=$countryCode&phone_number=$phoneNumber".toUri()

fun orderHistoryDeepLink() = "com.questrewards://user/order-history".toUri()

fun shopItemDetailsDeepLink(itemId: Long) = "com.questrewards://shop/$itemId".toUri()

fun defaultNavAnimation(block: ((NavOptions.Builder) -> Unit)? = null) =
    NavOptions.Builder().apply {
        block?.invoke(this)
    }.setEnterAnim(R.anim.acc_enter_anim)
        .setExitAnim(R.anim.acc_exit_anim)
        .setPopEnterAnim(R.anim.acc_pop_enter_anim)
        .setPopExitAnim(R.anim.acc_pop_exit_anim)
        .build()
