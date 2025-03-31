plugins {
    alias(libs.plugins.demo.android.library)
    alias(libs.plugins.demo.hilt)
}

android {
    namespace = "com.app.demo.di.common"
}
