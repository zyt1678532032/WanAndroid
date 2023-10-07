object Dependencies {
    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Androidx {
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.Androidx.constraintlayout}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.Androidx.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Androidx.appcompat}"

        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Androidx.lifecycle}"
        const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Androidx.lifecycle}"

        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.Androidx.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.Androidx.navigation}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val adapterRxjava3 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"
    }

    object Compose {
        const val material3 = "androidx.compose.material3:material3:${Versions.compose_material3}"

        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }

    object EventBus {
        const val eventbus = "org.greenrobot:eventbus:${Versions.eventbus}"
        const val annotationProcessor = "org.greenrobot:eventbus-annotation-processor:${Versions.eventbus}"
    }

    object Pluto {
        const val pluto = "com.plutolib:pluto:${Versions.pluto}"
        const val plutoNoOp = "com.plutolib:pluto-no-op:${Versions.pluto}"

        const val pluginsLayoutInspector = "com.plutolib.plugins:layout-inspector:${Versions.pluto}"
        const val pluginsLayoutInspectorNoOp = "com.plutolib.plugins:layout-inspector-no-op:${Versions.pluto}"
    }

    // MavenLocal中的自定义View
    const val myWidgets = "com.zyt.widgets:my-widgets:${Versions.myWidgets}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"

    const val junit = "junit:junit:${Versions.junit}"

    const val androidxTestExt = "androidx.test.ext:junit:${Versions.androidxTestExt}"
    const val androidxTestEspresso = "androidx.test.espresso:espresso-core:${Versions.androidxTestEspresso}"

    const val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutinesTest}"
}

