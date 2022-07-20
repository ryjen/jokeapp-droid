object Versions {
    object Sdk {
        const val Compile = 31
        const val Min = 21
        const val Target = 30
    }

    object ComposeX {
        const val Compiler = "1.1.1"
    }

    object Kotlin {
        const val version = "1.6.10"
    }

    object Gradle {
        const val version = "7.2.0"
    }
}

object Dependencies {

    object Gradle {
        const val Kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.version}"
        const val Android = "com.android.tools.build:gradle:${Versions.Gradle.version}"
        const val Serialization =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.version}"
        const val SqlDelight = "com.squareup.sqldelight:gradle-plugin:1.5.3"
    }

    object Kotlin {
        private const val version = "1.6.21"
        const val Test = "org.jetbrains.kotlin:kotlin-test-junit:$version"
    }

    const val AppCompat = "androidx.appcompat:appcompat:1.4.1"

    object AndroidX {
        const val Core = "androidx.core:core-ktx:1.7.0"
        const val Activity = "androidx.activity:activity-compose:1.4.0"
        const val Test = "androidx.arch.core:core-testing:2.1.0"
    }

    object NavigationX {
        private const val version = "2.4.2"
        const val Compose = "androidx.navigation:navigation-compose:$version"
        const val Test = "androidx.navigation:navigation-testing:$version"
    }

    object Koin {
        private const val version = "3.1.6"
        const val Android = "io.insert-koin:koin-android:$version"

        const val Compat = "io.insert-koin:koin-android-compat:$version"
        const val Compose = "io.insert-koin:koin-androidx-compose:$version"
        const val Ktor = "io.insert-koin:koin-ktor:$version"

        const val Test = "io.insert-koin:koin-test:$version"
        const val JvmTest = "io.insert-koin:koin-test-jvm:$version"
        const val JUnit = "io.insert-koin:koin-test-junit4:$version"
    }

    object Ktor {
        private const val version = "2.0.1"
        const val Content = "io.ktor:ktor-client-content-negotiation:$version"
        const val Android = "io.ktor:ktor-client-android:$version"
        const val Serialize = "io.ktor:ktor-client-serialization:$version"
        const val Json = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val Logging = "io.ktor:ktor-client-logging:$version"
        const val Client = "io.ktor:ktor-client-core:$version"
        const val Logback = "ch.qos.logback:logback-classic:1.2.11"
        const val test = "io.ktor:ktor-server-test-host:$version"

    }

    object SqlDelight {
        const val Android = "com.squareup.sqldelight:android-driver:1.5.3"
        const val Test = "com.squareup.sqldelight:sqlite-driver:1.5.3"
        const val Coroutines = "com.squareup.sqldelight:coroutines-extensions-jvm:1.5.3"
    }

    object Serialization {
        const val Json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
    }

    const val Material = "com.google.android.material:material:1.7.0"

    const val Timber = "com.jakewharton.timber:timber:5.0.1"

    object CoroutinesX {
        private const val version = "1.6.1-native-mt"
        const val Android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val Core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val Test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Accompanist {
        private const val version = "0.20.3"
        const val Insets = "com.google.accompanist:accompanist-insets:$version"
        const val Placeholder = "com.google.accompanist:accompanist-placeholder:$version"
        const val SystemUI = "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val Animation = "com.google.accompanist:accompanist-navigation-animation:$version"
    }

    object ComposeX {
        private const val version = "1.1.1"
        const val Ui = "androidx.compose.ui:ui:$version"
        const val Graphics = "androidx.compose.ui:ui-graphics:$version"
        const val Foundation = "androidx.compose.foundation:foundation-layout:$version"
        const val Material = "androidx.compose.material:material:$version"
        const val Animation = "androidx.compose.animation:animation:$version"
        const val Tooling = "androidx.compose.ui:ui-tooling:$version"
        const val Test = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object Espresso {
        private const val version = "3.4.0"
        const val Contrib = "androidx.test.espresso:espresso-contrib:$version"
        const val Core = "androidx.test.espresso:espresso-core:$version"
        const val Intents = "androidx.test.espresso:espresso-intents:$version"
    }

    object Mockk {
        private const val version = "1.12.3"
        const val Mockk = "io.mockk:mockk:$version"
        const val Android = "io.mockk:mockk-android:$version"
        const val Agent = "io.mockk:mockk-agent-jvm:$version"
    }

    const val Fake = "com.github.javafaker:javafaker:1.0.2"

    const val Truth = "com.google.truth:truth:1.1.3"

    const val JUnit = "junit:junit:4.13.2"

    const val Icons = "br.com.devsrsouza.compose.icons.android:font-awesome:1.0.0"

}

