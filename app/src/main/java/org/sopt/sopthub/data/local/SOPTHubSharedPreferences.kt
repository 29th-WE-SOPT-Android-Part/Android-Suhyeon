package org.sopt.sopthub.data.local

import android.content.Context
import android.content.SharedPreferences

object SOPTHubSharedPreferences {
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val IS_ON_BOARDING = "IS_ON_BOARDING"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(value: Boolean) {
        preferences.edit().putBoolean(AUTO_LOGIN, value).apply()
    }

    fun removeAutoLogin() {
        preferences.edit().remove(AUTO_LOGIN).apply()
    }

    fun getIsOnBoarding(): Boolean {
        return preferences.getBoolean(IS_ON_BOARDING, false)
    }

    fun setIsOnBoarding(value: Boolean) {
        preferences.edit().putBoolean(IS_ON_BOARDING, value).apply()
    }

    fun clearStorage() {
        preferences.edit().clear().apply()
    }
}