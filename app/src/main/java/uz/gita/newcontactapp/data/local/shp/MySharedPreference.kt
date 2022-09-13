package uz.gita.newcontactapp.data.local.shp

import android.content.Context
import android.content.SharedPreferences


class MySharedPreference private constructor(context: Context) {

    var token: String
        get() = sharedPreferences.getString("token", "").toString()
        set(token) {
            editor.putString("token", token).apply()
        }

    var name: String
        get() = sharedPreferences.getString("name", "").toString()
        set(name) {
            editor.putString("name", name).apply()
        }

    var password: String
        get() = sharedPreferences.getString("password", "").toString()
        set(password) {
            editor.putString("password", password).apply()
        }


    companion object {
        var mySharedPreference: MySharedPreference? = null
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        fun init(context: Context): MySharedPreference? {
            if (mySharedPreference == null) {
                mySharedPreference = MySharedPreference(context)
            }
            return mySharedPreference
        }

        fun getInstance() = mySharedPreference!!
    }

    init {
        sharedPreferences = context.getSharedPreferences("app_name", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }
}
