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
        const val Serialization = "org.jetbrains.kotlin:kotlin-serialization:1.5.31"
    }

    const val AppCompat = "androidx.appcompat:appcompat:1.4.1"

    object AndroidX {
        const val Core = "androidx.core:core-ktx:1.7.0"
        const val Activity = "androidx.activity:activity-compose:1.4.0"
        const val Test = "androidx.arch.core:core-testing:2.1.0"
    }

    object NavigationX {
        const val Compose = "androidx.navigation:navigation-compose:2.4.0"
        const val Test = "androidx.navigation:navigation-testing:2.4.0"
    }

    object RoomX {
        const val Runtime = "androidx.room:room-runtime:2.4.1"
        const val Kotlin = "androidx.room:room-ktx:2.4.1"
        const val Compiler = "androidx.room:room-compiler:2.4.1"
    }

    object Koin {
        private const val version = "3.1.5"
        const val Android = "io.insert-koin:koin-android:$version"

        const val Compat = "io.insert-koin:koin-android-compat:$version"
        const val Compose = "io.insert-koin:koin-androidx-compose:$version"
        const val Ktor = "io.insert-koin:koin-ktor:$version"

        const val Test = "io.insert-koin:koin-test:$version"
        const val JUnit = "io.insert-koin:koin-test-junit4:$version"

    }

    object Ktor {
        private const val version = "1.5.0"

        const val Android = "io.ktor:ktor-client-android:$version"
        const val Serialize = "io.ktor:ktor-client-serialization:$version"
        const val Logging = "io.ktor:ktor-client-logging:$version"
        const val Client = "io.ktor:ktor-client-core:1.6.7"
        const val Logback = "ch.qos.logback:logback-classic:1.2.10"
        const val test = "io.ktor:ktor-server-test-host:$version"

    }

    object Serialization {
        const val Json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
    }

    const val Material = "com.google.android.material:material:1.5.0"

    const val Timber = "com.jakewharton.timber:timber:5.0.1"

    object CoroutinesX {
        const val Android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0-native-mt"
        const val Core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt"
        const val Test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0-native-mt"
    }

    object Accompanist {
        private const val version = "0.20.3"
        const val Insets = "com.google.accompanist:accompanist-insets:$version"
        const val Placeholder = "com.google.accompanist:accompanist-placeholder:$version"
        const val SystemUI = "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

    object ComposeX {
        private const val version = "1.1.0"
        const val Ui = "androidx.compose.ui:ui:$version"
        const val Graphics = "androidx.compose.ui:ui-graphics:$version"
        const val Foundation = "androidx.compose.foundation:foundation-layout:$version"
        const val Material = "androidx.compose.material:material:$version"
        const val Animation = "androidx.compose.animation:animation:$version"
        const val Tooling = "androidx.compose.ui:ui-tooling:$version"
        const val Test = "androidx.compose.ui:ui-test-junit4:$version"
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

    object Mockk {
        private const val version = "1.12.2"
        const val Android = "io.mockk:mockk-android:$version"
        const val Agent = "io.mockk:mockk-agent-jvm:$version"

    }

    const val Fake = "com.github.javafaker:javafaker:1.0.2"


    const val Truth = "com.google.truth:truth:1.1.3"

    const val JUnit = "junit:junit:4.13.2"

    const val Icons = "br.com.devsrsouza.compose.icons.android:font-awesome:1.0.0"

}

