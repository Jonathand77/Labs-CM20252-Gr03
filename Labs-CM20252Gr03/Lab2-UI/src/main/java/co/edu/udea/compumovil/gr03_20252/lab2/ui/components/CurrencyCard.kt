package co.edu.udea.compumovil.gr03_20252.lab2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CurrencyCard(rates: Map<String, Double>?) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("💱 Divisas (USD)", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))

            when {
                rates == null -> {
                    Text(
                        "No se pudieron cargar las tasas de cambio.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                rates.isEmpty() -> {
                    Text(
                        "Cargando tasas de cambio...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                else -> {
                    rates.forEach { (currency, rate) ->
                        Text(
                            "$currency: $rate",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}