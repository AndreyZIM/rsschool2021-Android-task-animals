package com.example.animals.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.example.animals.R

//class SettingsFragment : PreferenceFragmentCompat(),
//    SharedPreferences.OnSharedPreferenceChangeListener {
//
//    var sharedPreferences: SharedPreferences? = null
//    var switchPreference: SwitchPreference? = null
//
//    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//        addPreferencesFromResource(R.xml.settings)
//
//        switchPreference = findPreference(SWITCH_KEY)
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
//        sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
//
//        switchPreference?.summary =
//            if (sharedPreferences?.getBoolean(SWITCH_KEY, true) == true) ROOM else CURSOR
//    }
//
//    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
//        if (key == SWITCH_KEY) switchPreference?.summary =
//            if (sharedPreferences?.getBoolean(SWITCH_KEY, true) == true) ROOM else CURSOR
//    }
//
//    companion object {
//        const val SWITCH_KEY = "switch"
//        const val ROOM = "Room"
//        const val CURSOR = "Cursor"
//    }
//}
