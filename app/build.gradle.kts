plugins {
    alias(libs.plugins.demo.android.application)
    alias(libs.plugins.demo.android.application.compose)
    alias(libs.plugins.demo.hilt)
    id(libs.plugins.kotlinx.serialization.get().pluginId)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.app.demo"

    defaultConfig {
        applicationId = "com.app.demo"
        versionCode = 1
        versionName = "1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    packaging {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":common:di"))
    implementation(project(":common:ui"))
    implementation(project(":common:navigation:api"))
    implementation(project(":common:navigation:wiring"))
    implementation(project(":feature:payment:presentation"))
    implementation(project(":feature:payment:wiring"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.material3)
}
