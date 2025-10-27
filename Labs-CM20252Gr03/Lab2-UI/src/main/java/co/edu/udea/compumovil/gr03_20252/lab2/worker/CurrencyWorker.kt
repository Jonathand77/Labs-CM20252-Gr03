package co.edu.udea.compumovil.gr03_20252.lab2.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import co.edu.udea.compumovil.gr03_20252.lab2.data.currency.RetrofitInstance
import com.google.gson.Gson
import androidx.core.content.edit

class CurrencyWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val response = RetrofitInstance.api.getRates("USD")
            val jsonRates = Gson().toJson(response.rates)

            // Guardar en SharedPreferences
            val prefs = applicationContext.getSharedPreferences("currency_prefs", Context.MODE_PRIVATE)
            prefs.edit { putString("rates", jsonRates) }

            Log.d("CurrencyWorker", "Rates saved: $jsonRates")
            Result.success()
        } catch (e: Exception) {
            Log.e("CurrencyWorker", "Failed to fetch/save rates: ${e.message}")
            Result.failure()
        }
    }
}