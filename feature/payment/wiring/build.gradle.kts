plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.demo.hilt)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.kotlinx.serialization.get().pluginId)
}

android {
    namespace = "com.app.demo.payment.wiring"
}

dependencies {
    implementation(project(":feature:payment:data"))
    implementation(project(":feature:payment:domain"))
    implementation(project(":feature:payment:presentation"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt.core)
    implementation(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)
}