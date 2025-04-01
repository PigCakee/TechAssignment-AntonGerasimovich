plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.demo.hilt)
}

android {
    namespace = "com.app.demo.navigation.impl"
}

dependencies {
    implementation(project(":common:navigation:api"))

    implementation(libs.androidx.core.ktx)
}