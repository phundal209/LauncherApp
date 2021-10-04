package com.launcher.app.data

import android.graphics.drawable.Drawable

/**
 * Data model representing what the Domain Model for the
 * app info looks like
 */
data class AppInfo(
    val packageName: String, // the name of package like com.whatever.app
    val packageIcon: Drawable, // the drawable image of the app
    val packageLabel: String, // the readable version of the name of the app like Instagram
    val isEnabled: Boolean = true // if the app is allowed to be launched on this device
) {
    fun withEnabled(enabled: Boolean) = copy(isEnabled = enabled)
}