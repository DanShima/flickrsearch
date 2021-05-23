import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.danshima.flickrsearch"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    val key: String = gradleLocalProperties(rootDir).getProperty("API_KEY")
    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", key)
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        val options = listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
        )
        freeCompilerArgs = freeCompilerArgs + options
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
        kotlinCompilerVersion = "1.4.32"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    //hilt
    implementation("com.google.dagger:dagger:2.35")
    annotationProcessor("com.google.dagger:dagger-compiler:2.35")
    implementation("com.google.dagger:hilt-android:2.35")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.28.3-alpha")
    annotationProcessor("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    //glide
    implementation("com.google.accompanist:accompanist-glide:0.9.1")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.6.4")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation("com.squareup.okhttp3:okhttp:4.2.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.0")
    //gson
    implementation("com.google.code.gson:gson:2.8.6")
    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha01")
    implementation("androidx.activity:activity-compose:1.3.0-alpha07")
    implementation ("androidx.activity:activity-ktx:1.2.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    // Jetpack Compose Integration for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha04")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
}