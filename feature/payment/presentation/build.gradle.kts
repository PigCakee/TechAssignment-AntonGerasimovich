plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.demo.payment.presentation"
}

dependencies {
    implementation(project(":feature:payment:domain"))

    implementation(libs.androidx.core.ktx)
}