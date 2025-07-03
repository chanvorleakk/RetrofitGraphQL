package com.example.retrofitgraphql.base
//
import android.content.Context
import android.content.SharedPreferences

abstract class BasePreference(context: Context, private val preferenceName: String) {

    private val mShare: SharedPreferences = context.getSharedPreferences(
        "com.example.retrofitgraphql.Base_${this.preferenceName}",
        Context.MODE_PRIVATE
    )

    private val mEditor: SharedPreferences.Editor = mShare.edit()

    fun save(key: String, value: Long) {
        mEditor.putLong(key, value)
        mEditor.commit()
    }

    fun save(key: String, value: String?) {
        mEditor.putString(key, value)
        mEditor.commit()
    }

    fun save(key: String, value: Boolean) {
        mEditor.putBoolean(key, value)
        mEditor.commit()
    }

    fun save(key: String, value: Int) {
        mEditor.putInt(key, value)
        mEditor.commit()
    }

    fun save(key: String, value: Float) {
        mEditor.putFloat(key, value)
        mEditor.commit()
    }

    operator fun get(key: String, def: String?): String? {
        return mShare.getString(key, def)
    }

    fun isWord(key: String, def: Boolean): Boolean {
        return mShare.getBoolean(key, def)
    }

    operator fun get(key: String, def: Int): Int {
        return mShare.getInt(key, def)
    }

    operator fun get(key: String, def: Long): Long {
        return mShare.getLong(key, def)
    }

    operator fun get(key: String, def: Float): Float {
        return mShare.getFloat(key, def)
    }

    fun `is`(key: String, def: Boolean): Boolean {
        return mShare.getBoolean(key, def)
    }

    fun clear() {
        mEditor.clear()
        mEditor.commit()
    }
}

