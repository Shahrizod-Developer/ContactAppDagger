package uz.gita.newcontactapp.data.remote.server

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.newcontactapp.app.App
import uz.gita.newcontactapp.data.remote.api.AuthApi
import uz.gita.newcontactapp.data.remote.api.ContactApi

class RetrofitInstance {

    companion object {

        val retrofit by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(logging)
                .addInterceptor(ChuckerInterceptor(App.instance)).build()

            Retrofit.Builder()
                .baseUrl("https://b751-92-38-100-70.eu.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }
}