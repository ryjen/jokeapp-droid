import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    compileSdk = Versions.Sdk.Compile

    defaultConfig {
        applicationId = "com.github.ryjen.jokeapp"
        minSdk = Versions.Sdk.Min
        targetSdk = Versions.Sdk.Target
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
    }
    buildFeatures {
        dataBinding = true
        compose = true

        // Disable unused AGP features
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
    signingConfigs {
        val props = gradleLocalProperties(rootDir)
        props.getProperty("signing.debug.file")?.let { keystore: String ->
            getByName("debug") {
                storeFile = file(keystore)
                storePassword = props.getProperty("signing.debug.store")
                keyPassword = props.getProperty("signing.debug.key")
                keyAlias = props.getProperty("signing.debug.alias")
            }
        }
        props.getProperty("signing.release.file")?.let { keystore: String ->
            create("release") {
                storeFile = file(keystore)
                storePassword = props.getProperty("signing.release.store")
                keyPassword = props.getProperty("signing.release.key")
                keyAlias = props.getProperty("signing.release.alias")
            }
        }
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
            signingConfigs.findByName("debug")?.let { debug ->
                signingConfig = debug
            }
        }
        getByName("release") {

            signingConfigs.findByName("release")?.let { release ->
                signingConfig = release
            }
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    testOptions {
        animationsDisabled = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.ComposeX.Compiler
    }

    buildToolsVersion = "31.0.0"
}

dependencies {
    kapt(Dependencies.RoomX.Compiler)

    implementation(Dependencies.AppCompat)

    implementation(Dependencies.AndroidX.Core)
    implementation(Dependencies.AndroidX.Activity)

    implementation(Dependencies.LifeCycleX.ViewModel)
    implementation(Dependencies.LifeCycleX.Compose)

    implementation(Dependencies.RoomX.Runtime)
    implementation(Dependencies.RoomX.Kotlin)

    implementation(Dependencies.WorkX.Runtime)
    implementation(Dependencies.Material)
    implementation(Dependencies.Gson)
    implementation(Dependencies.Timber)
    implementation(Dependencies.NavigationX.Compose)
    implementation(Dependencies.Accompanist.Insets)
    implementation(Dependencies.Accompanist.Placeholder)

    implementation(Dependencies.Ktor.Android)
    implementation(Dependencies.Ktor.Client)
    implementation(Dependencies.Ktor.Logging)
    implementation(Dependencies.Ktor.Serialize)
    implementation(Dependencies.Ktor.Json)
    implementation(Dependencies.Ktor.Logback)

    implementation(Dependencies.CoroutinesX.Android)
    implementation(Dependencies.CoroutinesX.Core)

    implementation(Dependencies.ComposeX.Material)
    implementation(Dependencies.ComposeX.Animation)
    implementation(Dependencies.ComposeX.Ui)
    implementation(Dependencies.ComposeX.Tooling)
    implementation(Dependencies.ComposeX.Foundation)

    implementation(Dependencies.Koin.Android)
    implementation(Dependencies.Koin.Compat)
    implementation(Dependencies.Koin.Compose)


    // UI Tests
    androidTestImplementation(Dependencies.ComposeX.Test)

    // Testing dependencies
    androidTestImplementation(Dependencies.AndroidX.Test)
    androidTestImplementation(Dependencies.Espresso.Contrib)
    androidTestImplementation(Dependencies.Espresso.Core)
    androidTestImplementation(Dependencies.Espresso.Intents)
    androidTestImplementation(Dependencies.TestX.JUnit)
    androidTestImplementation(Dependencies.TestX.UIAutomator)
    androidTestImplementation(Dependencies.WorkX.Test)
    androidTestImplementation(Dependencies.NavigationX.Test)
    androidTestImplementation(Dependencies.Truth)
    androidTestImplementation(Dependencies.CoroutinesX.Test)
    androidTestImplementation(Dependencies.Koin.Test)
    androidTestImplementation(Dependencies.Koin.JUnit)

    testImplementation(Dependencies.JUnit)
}

kapt {
    correctErrorTypes = true
}

configurations {
    all {
        exclude(group = "org.checkerframework", module = "checker")
    }
}
