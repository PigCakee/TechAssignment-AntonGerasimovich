plugins {
    id("demo.android.library")
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.demo.hilt)
}

android {
    namespace = "com.app.demo.navigation.wiring"
}

dependencies {
    implementation(project(":common:navigation:api"))
    implementation(project(":common:navigation:impl"))

    implementation(libs.androidx.core.ktx)
}