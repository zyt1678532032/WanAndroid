plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 26
        //noinspection OldTargetApi
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        register("release") {
            storeFile = file("/Users/rod.zhang/Desktop/key")
            keyAlias = "key0"
            storePassword = "zyt123.0"
            keyPassword = "zyt123.0"
        }
    }

    buildTypes {
        debug {

        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                "proguard-rules.pro",
                @Suppress("UnstableApiUsage") getDefaultProguardFile("proguard-android-optimize.txt"),
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true // 支持Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
}
// eventbus 注解处理器
kapt {
    arguments {
        arg("eventBusIndex", "com.example.myapp.MyEventBusIndex")
    }
}

dependencies {
    implementation(Dependencies.Androidx.coreKtx)
    implementation(Dependencies.Androidx.appcompat)
    implementation(Dependencies.Androidx.constraintlayout)
    implementation(Dependencies.Androidx.livedataKtx)
    implementation(Dependencies.Androidx.viewmodelKtx)
    implementation(Dependencies.Androidx.navigationFragmentKtx)
    implementation(Dependencies.Androidx.navigationUiKtx)

    implementation(Dependencies.Google.material)
    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.converterGson)
    implementation(Dependencies.Retrofit.adapterRxjava3)
    // Compose
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material3)
    // Compose Preview support
    implementation(Dependencies.Compose.uiToolingPreview)
    debugImplementation(Dependencies.Compose.uiTooling)
    // Shimmer
    implementation(Dependencies.shimmer)
    // Glide
    implementation(Dependencies.glide)
    // EventBus
    implementation(Dependencies.EventBus.eventbus)
    kapt(Dependencies.EventBus.annotationProcessor)

    // pluto
    debugImplementation(Dependencies.Pluto.pluto)
    releaseImplementation(Dependencies.Pluto.plutoNoOp)
    // Layout Inspector
    debugImplementation(Dependencies.Pluto.pluginsLayoutInspector)
    releaseImplementation(Dependencies.Pluto.pluginsLayoutInspectorNoOp)
    // leakcanary
    debugImplementation(Dependencies.leakcanary)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.kotlinxCoroutinesTest)
    androidTestImplementation(Dependencies.androidxTestExt)
    androidTestImplementation(Dependencies.androidxTestEspresso)
}