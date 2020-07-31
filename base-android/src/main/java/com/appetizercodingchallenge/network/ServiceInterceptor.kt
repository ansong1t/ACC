package com.appetizercodingchallenge.network

import android.content.SharedPreferences
import com.appetizercodingchallenge.util.getBearerToken
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request

/**
 * Helper class to automatically put bearer token to [Request] header
 *
 * Add `@Headers("No-Authentication: true")` on top of your retrofit
 * request if you don't want to pass bearer token
 *
 * @param sharedPref where bearer token is saved
 */
class ServiceInterceptor(private val sharedPref: SharedPreferences? = null) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            val token = sharedPref?.getBearerToken()
            if (!token.isNullOrEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }
        }
        return chain.proceed(request)
    }
}
