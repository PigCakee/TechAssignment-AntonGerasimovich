plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.demo.android.library.compose)
    id(libs.plugins.kotlinx.serialization.get().pluginId)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.app.demo.ui"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.serialization.json)
}
