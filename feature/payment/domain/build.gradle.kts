plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.demo.payment.domain"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit.junit)
}