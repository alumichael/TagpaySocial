package com.aegletec.tagpaysocial.data.network

import android.content.Context
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private const val BASE_URL = "http://206.189.161.140:8080/"
    }

    fun <Api> buildApi(
            api: Class<Api>,
            context: Context
    ): Api {
       // val authenticator = TokenAuthenticator(context, buildTokenApi())
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
    }

 /*   private fun buildTokenApi(): TokenRefreshApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TokenRefreshApi::class.java)
    }*/

    private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("accept", "*/*")
                    }.build())
                }.also { client ->
                    authenticator?.let { client.authenticator(it) }
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)

                }.build()
    }
}