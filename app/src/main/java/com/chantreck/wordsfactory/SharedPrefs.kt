package com.chantreck.wordsfactory

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPrefs {
    private const val PREFERENCES = "OnboardingPrefs"
    private const val ONBOARDING = "ONBOARDING_WATCHED"
    private const val USER = "USER_SAVED"
    private var preferences: SharedPreferences? = null

    fun setup(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES, MODE_PRIVATE)
    }

    fun saveOnboarding() {
        val instance = preferences ?: return
        with (instance.edit()) {
            putBoolean(ONBOARDING, true)
            apply()
        }
    }

    fun isOnboardingWatched(): Boolean {
        val instance = preferences ?: return false
        return instance.getBoolean(ONBOARDING, false)
    }

    fun saveUserInfo() {
        val instance = preferences ?: return
        with (instance.edit()) {
            putBoolean(USER, true)
            apply()
        }
    }

    fun isUserSaved(): Boolean {
        val instance = preferences ?: return false
        return instance.getBoolean(USER, false)
    }
}