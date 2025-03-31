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
    implementation(project(":common:ui"))
    implementation(project(":common:di"))
    implementation(project(":common:navigation:api"))
    implementation(project(":feature:payment:domain"))

    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.junit.params)
    testImplementation(libs.truth)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.testing)
    testImplementation(libs.turbine)
}