package com.appetizercodingchallenge.appinitializers

import android.app.Application
import com.appetizercodingchallenge.util.AppCoroutineDispatchers
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.zone.ZoneRulesProvider

class ThreeTenBpInitializer constructor(
    private val dispatchers: AppCoroutineDispatchers
) : AppInitializer {
    override fun init(application: Application) {
        // Init ThreeTenABP
        AndroidThreeTen.init(application)

        // Query the ZoneRulesProvider so that it is loaded on a background coroutine
        GlobalScope.launch(dispatchers.io) {
            ZoneRulesProvider.getAvailableZoneIds()
        }
    }
}
