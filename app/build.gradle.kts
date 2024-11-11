plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.dish"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dish"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.material)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    //Hilt Navigation Compose
    implementation(libs.hilt.navigation.compose)
    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    //Retrofit
    implementation(libs.retrofit)         // Retrofit core library
    implementation(libs.retrofit.gson)    // Retrofit Gson converter (for JSON parsing)
    implementation(libs.okhttp)           // OkHttp (HTTP client)
    implementation(libs.okhttp.logging)
    //Coroutine
    implementation(libs.coroutines.core)       // Core coroutines library
    implementation(libs.coroutines.android)
    // Lifecycle and ViewModel dependencies
    // Lifecycle components
    implementation(libs.lifecycle.viewmodel)    // ViewModel
    implementation(libs.lifecycle.livedata)      // LiveData (optional, if you plan to use it)
// Navigation Compose dependency
    implementation(libs.navigation.compose)      // Navigation for Jetpack Compose
    implementation(libs.coil.compose)

}