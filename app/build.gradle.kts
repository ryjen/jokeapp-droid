import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
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

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.ComposeX.Compiler
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildToolsVersion = "31.0.0"
    namespace = "com.github.ryjen.jokeapp"
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":ui"))
    implementation(project(":meta"))

    implementation(Dependencies.AppCompat)
    implementation(Dependencies.AndroidX.Activity)
    implementation(Dependencies.Koin.Android)

    androidTestImplementation(project(":test"))
    testImplementation(project(":test"))
}
