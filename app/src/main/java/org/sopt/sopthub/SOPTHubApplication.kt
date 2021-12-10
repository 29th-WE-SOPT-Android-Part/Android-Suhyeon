package org.sopt.sopthub

import android.app.Application
import org.sopt.sopthub.data.local.SOPTHubSharedPreferences

class SOPTHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SOPTHubSharedPreferences.init(applicationContext)
    }
}