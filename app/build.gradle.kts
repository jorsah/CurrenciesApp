plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.currenciesapp"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.currenciesapp"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core:navigation")))
    implementation(project(mapOf("path" to ":core:remote")))
    implementation(project(mapOf("path" to ":core:localdata")))
    implementation(project(mapOf("path" to ":uiKit")))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.work:work-runtime-ktx:2.7.1")
    implementation("com.google.android.material:material:1.6.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.10")

    /**
     * Coroutines
     */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

    /**
     * Compose
     */
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.compiler:compiler:1.2.0")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.compose.animation:animation:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.2.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    /**
     * JetPack
     */
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.2")
    implementation("androidx.navigation:navigation-compose:2.5.2")

    /**
     * Dagger
     */
    implementation("com.google.dagger:dagger:2.40.5")
    implementation("androidx.compose.material3:material3:1.1.1")
    kapt("com.google.dagger:dagger-android-processor:2.38.1")
    kapt("com.google.dagger:dagger-compiler:2.40.5")

    /**
     * Hilt
     */
    implementation("com.google.dagger:hilt-android:2.42")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.42")

    /**
     * Retrofit
     * */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
}