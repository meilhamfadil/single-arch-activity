package id.kudzoza.example.data.repository.source.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import id.kudzoza.core.AppConfig.DEFAULT_LANGUAGE
import id.kudzoza.core.PreferenceKey
import id.kudzoza.core.data.model.ProfileModel
import id.kudzoza.core.di.qualifier.EnvVersionCode
import id.kudzoza.core.di.qualifier.EnvVersionName
import id.kudzoza.core.util.json
import id.kudzoza.core.util.toObject
import javax.inject.Inject

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

class GlobalPreference
@Inject constructor(
    @EnvVersionName private val versionName: String,
    @EnvVersionCode private val versionCode: Int,
    private val preferences: SharedPreferences,
) {

    fun storeAccessToken(token: String) {
        preferences.edit {
            putString(PreferenceKey.KEY_TOKEN, token)
        }
    }

    fun getAccessToken(): String {
        return preferences.getString(PreferenceKey.KEY_TOKEN, "").orEmpty()
    }

    fun storeProfileData(profile: ProfileModel) {
        preferences.edit {
            putString(PreferenceKey.KEY_PROFILE, json(profile))
        }
    }

    fun getProfileData(): ProfileModel? {
        return toObject(
            preferences.getString(PreferenceKey.KEY_PROFILE, null),
            ProfileModel::class.java
        )
    }

    fun checkFirstOpen(): Boolean {
        return preferences.getBoolean(PreferenceKey.KEY_FIRST_TIME, true)
    }

    fun storeFirstOpen(value: Boolean) {
        preferences.edit {
            putBoolean(PreferenceKey.KEY_FIRST_TIME, value)
        }
    }

    fun checkThenUpdateFirstOpen(): Boolean {
        val currentValue = preferences.getBoolean(PreferenceKey.KEY_FIRST_TIME, true)
        if (currentValue)
            preferences.edit {
                putBoolean(PreferenceKey.KEY_FIRST_TIME, false)
            }
        return currentValue
    }

    fun getCurrentLanguage(): String {
        return preferences.getString(
            PreferenceKey.KEY_LANGUAGE,
            DEFAULT_LANGUAGE
        ) ?: DEFAULT_LANGUAGE
    }

    fun storeCurrentLanguage(lang: String) {
        preferences.edit {
            putString(PreferenceKey.KEY_LANGUAGE, lang)
        }
    }

    fun getCurrentVersionName(): String {
        return preferences.getString(
            PreferenceKey.KEY_VERSION_NAME,
            versionName
        ) ?: versionName
    }

    fun storeCurrentVersionName(name: String) {
        preferences.edit {
            putString(PreferenceKey.KEY_VERSION_NAME, name)
        }
    }

    fun getCurrentVersionCode(): Int {
        return preferences.getInt(
            PreferenceKey.KEY_VERSION_CODE,
            versionCode
        )
    }

    fun storeCurrentVersionCode(code: Int) {
        preferences.edit {
            putInt(PreferenceKey.KEY_VERSION_CODE, code)
        }
    }

}