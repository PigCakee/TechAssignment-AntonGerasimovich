plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.demo.hilt)
    alias(libs.plugins.demo.android.library.compose)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.app.demo.payment.presentation"
}

dependencies {
    implementation(project(":feature:payment:domain"))
    implementation(project(":common:ui"))
    implementation(project(":common:di"))

    implementation(libs.androidx.core.ktx)
}