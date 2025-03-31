plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.demo.payment.wiring"
}

dependencies {
    implementation(project(":feature:payment:data"))
    implementation(project(":feature:payment:domain"))
    implementation(project(":feature:payment:presentation"))

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit.junit)
}