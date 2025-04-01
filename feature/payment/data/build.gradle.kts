plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.demo.hilt)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.kotlinx.serialization.get().pluginId)
}

android {
    namespace = "com.app.demo.payment.data"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(project(":common:di"))
    implementation(project(":common:network"))
    implementation(project(":feature:payment:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.okhttp)

    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.junit.params)
    testImplementation(libs.truth)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.testing)
    testImplementation(libs.turbine)
}