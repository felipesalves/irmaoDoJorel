package br.com.irmaodojoreliesb.repository.network.connector

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Conector{

    fun criarConexao(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://www.irmaodojorelapi.site/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}