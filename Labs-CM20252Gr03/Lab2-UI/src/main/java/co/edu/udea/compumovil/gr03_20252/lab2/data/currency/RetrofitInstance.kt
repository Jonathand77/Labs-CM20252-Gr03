package co.edu.udea.compumovil.gr03_20252.lab2.data.currency

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: ExchangeRateService by lazy {
        Retrofit.Builder()
            .baseUrl("https://open.er-api.com/v6/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRateService::class.java)
    }
}