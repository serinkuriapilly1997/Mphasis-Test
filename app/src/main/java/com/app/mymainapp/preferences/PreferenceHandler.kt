package com.app.mymainapp.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject



class PreferenceHandler @Inject constructor(
    val sharedPreferences: SharedPreferences
) {

    var userToken: String
        get() = sharedPreferences.getString("token", "") ?: ""
        set(value) = sharedPreferences.edit { putString("token", value) }
}