# **📱 Navigation Jetpack Compose - Lab2 UI**

---

## 🛠️ Stack tecnológico y Arquitectura

![Kotlin](https://img.shields.io/badge/Kotlin-Language-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI%20Toolkit-4285F4?logo=jetpackcompose&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-Networking-48B983)
![WorkManager](https://img.shields.io/badge/WorkManager-Background%20Tasks-3DDC84?logo=android&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20(UI%20%2F%20ViewModel%20%2F%20Data)-blue)
![GitHub repo size](https://img.shields.io/github/repo-size/jonathand77/LabsCM20252-Gr03)
![GitHub contributors](https://img.shields.io/github/contributors/jonathand77/LabsCM20252-Gr03)
![GitHub last commit](https://img.shields.io/github/last-commit/jonathand77/LabsCM20252-Gr03)
![Languages](https://img.shields.io/github/languages/count/jonathand77/LabsCM20252-Gr03)

## 👥 Integrantes

| 👨‍💻 Nombre | 📧 Correo | 🐙 Usuario GitHub |
|---|---|---|
| **Jonathan David Fernandez Vargas** | jonathand.fernandez@udea.edu.co | [jonathand77](https://github.com/jonathand77) |
| **Valeria Alvarez Fernandez** | valeria.alvarezf@udea.edu.co | [vaf88](https://github.com/vaf88) |
| **Santiago Arenas Gomez** | santiago.arenas1@udea.edu.co | [sag0719](https://github.com/sag0719) |
| **Maria Valentina Quiroga Alzate** | Valentina.quiroga1@udea.edu.co | [Valentina-Q-A](https://github.com/Valentina-Q-A) |

**Aplicación Android de noticias (basada en el sample JetNews de Google) con navegación mediante Jetpack Navigation Compose, conversión de divisas en tiempo real y un widget de escritorio.**

---

## 1. 🔍 Introducción

Este proyecto explora la librería **Navigation** de Jetpack, y en particular el artefacto `navigation-compose`, que permite una navegación idiomática y consistente entre pantallas dentro de una app construida completamente con Jetpack Compose.

La app está estructurada en capas siguiendo **MVVM**: una capa de **datos** (repositorios de posts e intereses, más un cliente Retrofit para tasas de cambio), una capa de **ViewModel** (gestión de estado reactivo con `StateFlow`) y una capa de **UI** (pantallas y componentes en Compose). Además, incorpora un `CurrencyWorker` (WorkManager) para actualizar tasas de cambio en segundo plano y un widget de escritorio implementado con **Glance**.

## 2. ⚙️ Requisitos Previos

Antes de comenzar, asegúrate de contar con:
- Git
- [Android Studio](https://developer.android.com/studio) (Giraffe o superior)
- JDK 17
- Android SDK con `compileSdk 35`, `minSdk 21` y `targetSdk 33` instalados (Android Studio los descarga automáticamente vía SDK Manager)
- Un emulador Android (API 33/34) o un dispositivo físico con depuración USB habilitada

## 📦 Estructura del Proyecto

```
Labs-CM20252-Gr03/
├── RAÍZ
│   ├── .gitignore
│   ├── .git/
│   ├── LICENSE
│   ├── README.md
│   └── Labs-CM20252Gr03/
│       ├── build.gradle.kts
│       ├── settings.gradle.kts
│       ├── gradle.properties
│       ├── gradlew / gradlew.bat
│       ├── gradle/
│       │   └── libs.versions.toml
│       └── Lab2-UI/
│           ├── build.gradle.kts
│           ├── proguard-rules.pro
│           └── src/
│               ├── main/
│               │   ├── AndroidManifest.xml
│               │   └── java/co/edu/udea/compumovil/gr03_20252/lab2/
│               │       ├── data/
│               │       │   ├── currency/          # Retrofit: ExchangeRateService, RetrofitInstance
│               │       │   ├── interests/          # InterestsRepository + implementación fake
│               │       │   └── posts/              # PostsRepository + implementación fake
│               │       ├── glance/                 # Widget de escritorio (JetnewsGlanceAppWidget)
│               │       ├── model/                  # Post, PostsFeed
│               │       ├── ui/
│               │       │   ├── article/            # ArticleScreen, PostContent
│               │       │   ├── components/         # AppNavRail, CurrencyCard, Snackbar
│               │       │   ├── home/                # HomeRoute, HomeViewModel, PostCards
│               │       │   ├── interests/           # InterestsRoute, InterestsViewModel
│               │       │   ├── theme/               # Color, Shape, Type, Theme
│               │       │   ├── JetnewsApp.kt
│               │       │   ├── JetnewsNavGraph.kt
│               │       │   └── MainActivity.kt
│               │       ├── worker/                 # CurrencyWorker (WorkManager)
│               │       └── utils/
│               ├── test/
│               └── androidTest/
```

---

## 3. 🖥️ Guía Paso a Paso para Levantar el Proyecto

### 3.1 Clonar el repositorio

```bash
git clone https://github.com/jonathand77/LabsCM20252-Gr03.git
cd LabsCM20252-Gr03
```

### 3.2 Abrir en Android Studio

1. Abrir **Android Studio** → `File > Open` → seleccionar la carpeta `Labs-CM20252Gr03` (la que contiene `build.gradle.kts` y `settings.gradle.kts`).
2. Esperar a que **Gradle sincronice** las dependencias (descarga automática de Compose BOM, Retrofit, WorkManager, etc.).

### 3.3 Configurar un emulador o dispositivo

**⚠️ Nota:** No se requiere backend propio ni Docker. El módulo de divisas consume directamente la API pública [open.er-api.com](https://open.er-api.com), sin necesidad de API key.

- **Emulador:** `Tools > Device Manager` → crear un dispositivo virtual con imagen de sistema Android 13/14.
- **Dispositivo físico:** activar **Opciones de desarrollador** → Depuración USB → conectar por cable y seleccionarlo en Android Studio.

### 3.4 Ejecutar la app

Seleccionar el módulo `Lab2-UI` como configuración de ejecución y presionar ▶ **Run**.

Alternativamente, desde línea de comandos (requiere `ANDROID_HOME` configurado):

```bash
cd Labs-CM20252Gr03
./gradlew assembleDebug
./gradlew installDebug
```

---

## 4. 🗄️ Módulos de Datos y Buenas Prácticas

### 4.1 Fuentes de datos principales
| Módulo | Tipo | Descripción |
|---|---|---|
| `PostsRepository` | Repositorio local (fake) | Provee el feed de noticias/posts mostrado en Home |
| `InterestsRepository` | Repositorio local (fake) | Gestiona los temas de interés seleccionables |
| `ExchangeRateService` | Cliente Retrofit | Consulta tasas de cambio en tiempo real vía `open.er-api.com` |
| `CurrencyWorker` | WorkManager | Actualiza periódicamente las tasas de cambio en segundo plano |

### 4.2 Buenas Prácticas y Arquitectura
- **Separación clara de capas**: `ui/` (pantallas y componentes), `ui/*ViewModel` (estado con `StateFlow`), `data/` (repositorios y fuentes remotas).
- **Networking desacoplado**: `RetrofitInstance` centraliza la configuración del cliente HTTP, separado de la definición del servicio (`ExchangeRateService`).
- **Reactividad**: uso de `StateFlow` para exponer el estado de UI de forma reactiva desde los ViewModels.
- **Consistencia visual**: `MaterialTheme` con colores, tipografía y formas personalizados (`ui/theme/`).
- **Extensibilidad**: widget de escritorio (Glance) reutiliza los mismos modelos de datos que la app principal.
- **Validación de navegación**: comprobación de campos/estado antes de permitir transiciones entre pantallas.

## 5. 🧭 Navegación - Pantallas principales

| Pantalla | Descripción |
|---|---|
| `HomeRoute` / `HomeScreens` | Feed principal de posts, con tarjetas destacadas y "tu red" |
| `ArticleScreen` | Detalle de un post/artículo seleccionado |
| `InterestsRoute` / `InterestsScreen` | Selección de temas de interés por sección |
| `CurrencyCard` | Tarjeta con la tasa de cambio actual obtenida vía Retrofit |

## 6. 🎨 Widget - Jetpack Glance

### 6.1 Funcionalidades
- Muestra un resumen de posts recientes directamente en la pantalla de inicio del dispositivo.
- Se actualiza usando el mismo repositorio de datos que la app principal.
- Theming propio (`glance/ui/theme/`) consistente con Material 3.

### 6.2 Componentes clave
- **JetnewsGlanceAppWidget**: definición del widget y su contenido.
- **JetnewsGlanceAppWidgetReceiver**: receiver que registra el widget ante el sistema Android.
- **Post / Divider**: composables Glance reutilizables dentro del widget.

---
## **Fin de la guía y manual de usuario.**
---
