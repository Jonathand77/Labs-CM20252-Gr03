package co.edu.udea.compumovil.gr03_20252.lab2.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr03_20252.lab2.data.currency.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val _rates = MutableStateFlow<Map<String, Double>>(emptyMap())
    val rates: StateFlow<Map<String, Double>> = _rates

    private val allowedSymbols = listOf("EUR", "COP")

    fun fetchRates(base: String = "USD") {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRates(base)
                val filteredRates = response.rates.filterKeys { it in allowedSymbols }

                _rates.value = filteredRates
            } catch (e: Exception) {
                Log.e("CurrencyViewModel", "Error fetching rates: ${e.message}")
            }
        }
    }

    fun loadRatesFromPrefs(context: Context) {
        val prefs = context.getSharedPreferences("currency_prefs", Context.MODE_PRIVATE)
        val json = prefs.getString("rates", null)

        if (json != null) {
            try {
                val mapType = object : TypeToken<Map<String, Double>>() {}.type
                val allRates: Map<String, Double> = Gson().fromJson(json, mapType)
                val filteredRates = allRates.filterKeys { it in allowedSymbols }

                _rates.value = filteredRates
                Log.d("CurrencyViewModel", "Loaded rates from prefs: $filteredRates")
            } catch (e: Exception) {
                Log.e("CurrencyViewModel", "Error loading rates from prefs: ${e.message}")
            }
        } else {
            Log.w("CurrencyViewModel", "No saved rates found in prefs")
        }
    }
}