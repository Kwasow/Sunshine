import java.util.Properties

plugins {
    kotlin("android")
    kotlin("plugin.serialization")

    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
    id("io.gitlab.arturbosch.detekt") version("1.23.8")
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.10"
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
}

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "secrets.default.properties"
}

android {
    namespace = "pl.kwasow.sunshine"
    compileSdk = 35

    defaultConfig {
        applicationId = "pl.kwasow.sunshine"
        minSdk = 26
        targetSdk = 35
        versionCode = 39
        versionName = "2.1.6"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            // App config
            val properties = Properties()
            properties.load(project.rootProject.file("secrets.properties").inputStream())

            buildConfigField(
                "String",
                "BASE_URL",
                properties.getProperty("BASE_URL"),
            )
            buildConfigField(
                "String",
                "GOOGLE_WEB_CLIENT_ID",
                properties.getProperty("GOOGLE_WEB_CLIENT_ID"),
            )
            buildConfigField(
                "String",
                "RELATIONSHIP_START",
                properties.getProperty("RELATIONSHIP_START"),
            )
        }

        debug {
            isMinifyEnabled = false

            versionNameSuffix = "-beta"
            applicationIdSuffix = ".beta"

            // App config
            val properties = Properties()
            properties.load(project.rootProject.file("secrets.properties").inputStream())

            buildConfigField(
                "String",
                "BASE_URL",
                properties.getProperty("DEVELOPMENT_BASE_URL"),
            )
            buildConfigField(
                "String",
                "GOOGLE_WEB_CLIENT_ID",
                properties.getProperty("GOOGLE_WEB_CLIENT_ID"),
            )
            buildConfigField(
                "String",
                "RELATIONSHIP_START",
                properties.getProperty("RELATIONSHIP_START"),
            )
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {
    val composeBom = "2025.02.00"
    val exoplayerVersion = "1.5.1"
    val firebaseBom = "33.10.0"
    val koinVersion = "4.0.2"
    val ktorVersion = "3.1.0"

    // BoM
    implementation(platform("androidx.compose:compose-bom:$composeBom"))
    implementation(platform("com.google.firebase:firebase-bom:$firebaseBom"))

    // Firebase and Google
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.android.gms:play-services-maps:19.1.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.maps.android:maps-compose:6.4.2")

    // Kotlin
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    // Ktor
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")

    // Koin
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose-navigation:$koinVersion")

    // Compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.navigation:navigation-compose:2.8.8")
    implementation("com.google.accompanist:accompanist-permissions:0.37.2")

    // Other
    implementation("androidx.credentials:credentials:1.5.0-rc01")
    implementation("androidx.credentials:credentials-play-services-auth:1.5.0-rc01")
    implementation("androidx.media3:media3-common:$exoplayerVersion")
    implementation("androidx.media3:media3-exoplayer:$exoplayerVersion")
    implementation("androidx.media3:media3-session:$exoplayerVersion")
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("dev.chrisbanes.haze:haze:1.3.1")
    implementation("dev.chrisbanes.haze:haze-materials:1.3.1")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeBom"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
