package com.appetizercodingchallenge.appinitializers

import android.app.Application
import com.appetizercodingchallenge.util.AccLogger

class TimberInitializer constructor(
    private val qrLogger: AccLogger
) : AppInitializer {
    override fun init(application: Application) = qrLogger.setup()
}
