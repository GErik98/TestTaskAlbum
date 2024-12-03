plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.testtask"
    compileSdk = 35

    packaging {
        resources {
            excludes += "META-INF/androidx.cardview_cardview.version"
        }
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.example.testtask"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"${project.findProperty("API_KEY") ?: "default_app_id"}\"")
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.databinding:databinding-runtime:8.7.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.android.support:cardview-v7:28.0.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // ViewModel + viewModelScope
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1") // LiveData
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Coroutines for Android
    implementation ("com.github.bumptech.glide:glide:4.16.0") // Image loading library
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

}
