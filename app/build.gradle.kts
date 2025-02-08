plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.weatherapp_ui"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weatherapp_ui"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14" // Ensure this matches the Compose version you're using
    }

}

dependencies {
    // Core Android libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.5.3") // Updated Compose UI version
    implementation("androidx.compose.material3:material3:1.1.1") // Latest version of Material3
    implementation("androidx.compose.foundation:foundation:1.5.3") // Ensure the version matches
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3") // Ensure the version matches

    // Lifecycle and Activity
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // JSON Parsing
    implementation("org.json:json:20210307")

    // Compose BOM (Bill of Materials) to manage versions for Compose dependencies
    implementation(platform("androidx.compose:compose-bom:2023.01.00")) // Ensure BOM is in place for version consistency
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.8.0")

    // (Optional) Compose preview support
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
}


