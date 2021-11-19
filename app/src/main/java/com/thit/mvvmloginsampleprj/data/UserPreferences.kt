package com.thit.mvvmloginsampleprj.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

class UserPreferences @Inject constructor(@ApplicationContext context: Context) {
    private val applicationContext = context.applicationContext

    val authToken: Flow<String?>
        get() = applicationContext.dataStore.data.map { prefs -> prefs[KEY_AUTH] }

    suspend fun saveAuthToken(authToken: String) {
        applicationContext.dataStore.edit { mutablePreferences ->
            mutablePreferences[KEY_AUTH] = authToken
        }
    }

    companion object {
        private val KEY_AUTH = stringPreferencesKey("authTokenKey")
    }
}