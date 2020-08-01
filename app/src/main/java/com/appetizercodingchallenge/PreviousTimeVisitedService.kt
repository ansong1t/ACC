package com.appetizercodingchallenge

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.appetizercodingchallenge.util.getPref
import com.appetizercodingchallenge.util.recordPreviousVisited

class PreviousTimeVisitedService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        getPref().recordPreviousVisited()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        getPref().recordPreviousVisited()
        stopSelf()
    }
}
