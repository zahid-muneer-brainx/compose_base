plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.screenlake.ktormodule"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility =  JavaVersion.VERSION_18
        targetCompatibility =  JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }
}

dependencies {

    implementation (libs.core.ktx)
    implementation(libs.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    

    //Ktor dependencies
    implementation(libs.ktor.client.core)
    // Client Engine
    implementation(libs.ktor.client.cio)
    //Android Client
    implementation(libs.ktor.client.android)
    // HTTP engine: The HTTP client used to perform network requests.
    implementation(libs.ktor.client.okhttp)
// The serialization engine used to convert objects to and from JSON.
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.serialization)
// Logging
    implementation(libs.ktor.client.logging)
    // content Negotiation
    implementation(libs.ktor.client.content.negotiation)
    // Json
    implementation(libs.ktor.serialization.kotlinx.json)
    //XML
    implementation(libs.ktor.serialization.kotlinx.xml)


    implementation(libs.kotlinx.serialization.json)

    api(libs.gson)


    // Dependency Injection
    //koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.android.compose)
    implementation(libs.koin.android.compose.navigation)

}