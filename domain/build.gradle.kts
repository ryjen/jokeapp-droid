plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    compileSdk = Versions.Sdk.Compile
}

dependencies {
    implementation(project(":meta"))
    implementation(Dependencies.Koin.Android)
    implementation(Dependencies.Koin.Compat)

    implementation(Dependencies.CoroutinesX.Android)
    implementation(Dependencies.CoroutinesX.Core)
    implementation(Dependencies.Timber)
    implementation(Dependencies.Serialization.Json)

}
