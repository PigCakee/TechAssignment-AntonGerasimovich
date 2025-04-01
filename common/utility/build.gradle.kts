plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.demo.utility"
}

dependencies {

    implementation(libs.androidx.core.ktx)
}