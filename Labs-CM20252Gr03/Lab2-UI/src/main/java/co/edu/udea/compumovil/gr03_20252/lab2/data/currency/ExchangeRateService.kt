package co.edu.udea.compumovil.gr03_20252.lab2.data.currency

import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateService {
    @GET("latest/{base}")
    suspend fun getRates(
        @Path("base") base: String
    ): ExchangeRateResponse
}