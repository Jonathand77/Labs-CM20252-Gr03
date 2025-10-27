package co.edu.udea.compumovil.gr03_20252.lab2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import co.edu.udea.compumovil.gr03_20252.lab2.JetnewsApplication
import co.edu.udea.compumovil.gr03_20252.lab2.worker.CurrencyWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // 🔁 Registrar el Worker que obtiene las tasas de cambio cada 1 hora
        val currencyWorkRequest = PeriodicWorkRequestBuilder<CurrencyWorker>(
            24, TimeUnit.HOURS
        ).build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "CurrencyFetchWork",
            ExistingPeriodicWorkPolicy.KEEP,
            currencyWorkRequest
        )

        // UI setup
        val appContainer = (application as JetnewsApplication).container
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            JetnewsApp(appContainer, widthSizeClass)
        }
    }
}