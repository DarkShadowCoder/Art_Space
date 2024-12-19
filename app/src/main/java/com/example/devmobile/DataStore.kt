package com.example.devmobile

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Configuration du DataStore
val Context.dataStore by preferencesDataStore(name = "user_preferences")

suspend fun saveQuery(context: Context, query: String) {
    val queryKey = stringPreferencesKey("search_query")
    context.dataStore.edit { preferences ->
        preferences[queryKey] = query
    }
}

suspend fun loadQuery(context: Context): String {
    val queryKey = stringPreferencesKey("search_query")
    val preferences: Preferences = context.dataStore.data.first()
    return preferences[queryKey] ?: ""
}