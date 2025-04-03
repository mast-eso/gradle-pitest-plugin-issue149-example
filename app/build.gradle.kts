
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("pl.droidsonroids.pitest") version "0.2.20"
}

android {
    namespace = "com.example.pitestdemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pitestdemo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

afterEvaluate {
    tasks.getByName("pitestDebug").dependsOn("compileDebugKotlin", "compileReleaseKotlin", "compileReleaseJavaWithJavac")
}

pitest {
    failWhenNoMutations = false
}

dependencies {
    implementation(project(":otherProject"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}