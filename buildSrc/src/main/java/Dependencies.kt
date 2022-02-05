object Versions {
    object Sdk {
        const val Compile = 31
        const val Min = 21
        const val Target = 30
    }

    object ComposeX {
        const val Compiler = "1.0.5"
    }
}

object Dependencies {
    object Gradle {
        const val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
        const val Android = "com.android.tools.build:gradle:7.1.0"
        const val Ktor = "org.jetbrains.kotlin:kotlin-serialization:1.5.31"
    }

    const val AppCompat = "androidx.appcompat:appcompat:1.4.1"

    object AndroidX {
        const val Core = "androidx.core:core-ktx:1.7.0"
        const val Activity = "androidx.activity:activity-compose:1.4.0"
        const val Test = "androidx.arch.core:core-testing:2.1.0"
    }

    object LifeCycleX {
        const val ViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
        const val Compose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0"
    }

    object NavigationX {
        const val Compose = "androidx.navigation:navigation-compose:2.4.0"
        const val Test = "androidx.navigation:navigation-testing:2.4.0"
    }

    object RoomX {
        const val Runtime = "androidx.room:room-runtime:2.4.1"
        const val Compiler = "androidx.room:room-ktx:2.4.1"
    }

    object Koin {
        const val version = "3.1.5"
        const val Android = "io.insert-koin:koin-android:$version"

        // Java Compatibility
        const val Compat = "io.insert-koin:koin-android-compat:$version"
        // Jetpack WorkManager
        const val WorkManager = "io.insert-koin:koin-androidx-workmanager:$version"
        // Navigation Graph
        const val Navigation = "io.insert-koin:koin-androidx-navigation:$version"
        // Jetpack Compose
        const val Compose = "io.insert-koin:koin-androidx-compose:$version"

        const val Test = "io.insert-koin:koin-test:$version"
        // Needed JUnit version
        const val JUnit = "io.insert-koin:koin-test-junit4:$version"

    }

    object Ktor {
        const val version = "1.5.0"

        const val Android = "io.ktor:ktor-client-android:$version"
        const val Client = "io.ktor:ktor-client-serialization:$version"
        const val Serialize = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
        const val Logging = "io.ktor:ktor-client-logging-jvm:1.5.0"
    }

    const val Material = "com.google.android.material:material:1.5.0"

    const val Gson = "com.google.code.gson:gson:2.8.9"

    const val Timber = "com.jakewharton.timber:timber:5.0.1"

    object OkHttp {
        const val Log = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3"
        const val Mockserver = "com.squareup.okhttp3:mockwebserver:5.0.0-alpha.3"
    }

    object Retrofit {
        const val Converter = "com.squareup.retrofit2:converter-gson:2.9.0"
        const val Core = "com.squareup.retrofit2:retrofit:2.9.0"
    }

    object CoroutinesX {
        const val Android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0-native-mt"
        const val Core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt"
        const val Test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0-native-mt"
    }

    const val Accompanist = "com.google.accompanist:accompanist-insets:0.20.3"

    object ComposeX {
        const val Ui = "androidx.compose.ui:ui:1.0.5"
        const val Foundation = "androidx.compose.foundation:foundation:1.0.5"
        const val Material = "androidx.compose.material:material:1.0.5"
        const val Animation = "androidx.compose.animation:animation:1.0.5"
        const val Tooling = "androidx.compose.ui:ui-tooling:1.0.5"
        const val Test = "androidx.compose.ui:ui-test-junit4:1.0.5"
    }

    object Espresso {
        const val Contrib = "androidx.test.espresso:espresso-contrib:3.4.0"
        const val Core = "androidx.test.espresso:espresso-core:3.4.0"
        const val Intents = "androidx.test.espresso:espresso-intents:3.4.0"
    }

    object TestX {
        const val JUnit = "androidx.test.ext:junit:1.1.3"
        const val UIAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"
    }

    object WorkX {
        const val Runtime = "androidx.work:work-runtime-ktx:2.7.1"
        const val Test = "androidx.work:work-testing:2.7.1"
    }

    const val Truth = "com.google.truth:truth:1.1.3"

    const val JUnit = "junit:junit:4.13.2"
}
