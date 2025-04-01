plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.kotlinx.serialization.get().pluginId)
}

android {
    namespace = "com.app.demo.network"
}

dependencies {
    implementation(project(":common:ui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
}