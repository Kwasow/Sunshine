// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.8.2" apply false
    kotlin("android") version "2.1.10" apply false
    kotlin("plugin.serialization") version "2.1.10" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}
