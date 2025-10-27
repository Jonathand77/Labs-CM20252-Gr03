package co.edu.udea.compumovil.gr03_20252.lab2.data.currency

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse(
    @SerializedName("base_code")
    val base: String,

    @SerializedName("rates")
    val rates: Map<String, Double>
)