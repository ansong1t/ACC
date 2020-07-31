package com.appetizercodingchallenge.appinitializers

import android.app.Application

class AppInitializers constructor(
    private val initializers: Set<AppInitializer>
) {
    fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}
