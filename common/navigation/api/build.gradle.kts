plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.kotlinx.serialization.get().pluginId)
}

android {
    namespace = "com.app.demo.navigation.api"
}

dependencies {
    implementation(project(":common:ui"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.core.ktx)
}